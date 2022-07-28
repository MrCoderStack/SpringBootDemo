package com.mrcoder.sbdistributedlockannotations.annotation;

import com.mrcoder.sbdistributedlockannotations.model.R;
import com.mrcoder.sbdistributedlockannotations.util.AopUtil;
import com.mrcoder.sbdistributedlockannotations.util.SpelUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 分布式锁切面类
 */
@Slf4j
@Aspect
@Component
public class DistributedLockAop {

    @PostConstruct
    public void init() {
        log.info("DistributedLockAop init ......");
    }

    @Autowired(required = false)
    private Redisson redisson;

    private static final String LOCK_KEY_PREFIX = "LOCK:";

    /**
     * 拦截加了DistributedTask注解的方法请求
     *
     * @param joinPoint
     * @param distributedLock
     * @return
     * @throws Throwable
     */
    @Around("@annotation(distributedLock)")
    public Object around(ProceedingJoinPoint joinPoint, DistributedLock distributedLock) throws Throwable {
        Object result = null;

        String lockKey = LOCK_KEY_PREFIX + SpelUtil.generateKeyBySpEL(distributedLock.lockKey(), joinPoint);

        RLock lock = this.redisson.getLock(lockKey);

        boolean locked = false;
        try {
            // 尝试获取分布式锁, 并设置超时时间
            locked = lock.tryLock(0, distributedLock.expireTime(), TimeUnit.SECONDS);
            if (!locked) {
                log.info("#Redisson分布式锁# 加锁失败: [lockKey: {}, ThreadName :{}]", lockKey, Thread.currentThread().getName());
                // 判断目标方法是否有返回值
                if (AopUtil.isReturnVoid(joinPoint)) {
                    return null;
                } else {
                    return R.fail(distributedLock.message());
                }
            }
            log.info("#Redisson分布式锁# 加锁成功: [lockKey: {}, ThreadName :{}]", lockKey, Thread.currentThread().getName());

            // 执行目标方法
            result = joinPoint.proceed();
        } finally {
            if (locked) {
                lock.unlock();
                log.info("#Redisson分布式锁# 解锁成功: [lockKey: {}, ThreadName :{}]", lockKey, Thread.currentThread().getName());
            }
        }
        return result;
    }
}
