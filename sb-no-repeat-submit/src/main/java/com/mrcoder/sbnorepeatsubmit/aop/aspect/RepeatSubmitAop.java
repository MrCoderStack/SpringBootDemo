package com.mrcoder.sbnorepeatsubmit.aop.aspect;

import com.mrcoder.sbnorepeatsubmit.aop.annotation.NoRepeatSubmit;
import com.mrcoder.sbnorepeatsubmit.model.R;
import com.mrcoder.sbnorepeatsubmit.util.RedisCacheUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 防止重复提交切面类
 */
@Aspect
@Slf4j
@Component
public class RepeatSubmitAop {

    @Autowired
    private RedisCacheUtil redisCacheUtil;

    @PostConstruct
    public void init() {
        log.info("RepeatSubmitAop init ......");
    }

    /**
     * 拦截加了NoRepeatSubmit注解的方法请求
     *
     * @param joinPoint
     * @param noRepeatSubmit
     * @return
     * @throws Throwable
     */
    @Around("@annotation(noRepeatSubmit)")
    public Object beforeInvoce(ProceedingJoinPoint joinPoint, NoRepeatSubmit noRepeatSubmit) throws Throwable {

        Object result = null;

        // 方法名
        String methodName = joinPoint.getSignature().getName();

        // 缓存key
        String redisKey = createRequestRedisKey() + noRepeatSubmit.lockKey();

        // 唯一字符串作为缓存value
        String clientId = getClientId();

        log.info("----缓存key: {}----", redisKey);
        log.info("----缓存time: {}----", noRepeatSubmit.lockTime());
        // 获取锁, 并设置超时时间
        boolean locked = this.redisCacheUtil.setNx(redisKey, clientId, noRepeatSubmit.lockTime(), TimeUnit.SECONDS);
        log.info("----锁状态: {}----", locked);
        if (!locked) {
            System.out.println("请勿重复提交");
            return R.fail("请勿重复提交");
        }

        try {
            log.info("执行目标方法: {} ==>>开始......", methodName);
            result = joinPoint.proceed();
            log.info("执行目标方法: {} ==>>结束......", methodName);

            // 返回通知
            log.info("目标方法 " + methodName + " 执行结果 " + result);
        } finally {
            // 释放锁
            if (clientId.equals(this.redisCacheUtil.get(redisKey))) {
                this.redisCacheUtil.delKey(redisKey);
            }
        }
        // 后置通知
        log.info("目标方法 " + methodName + " 结束");

        return result;
    }

    /**
     * 生成锁的RedisKey
     *
     * @return
     */
    private String createRequestRedisKey() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        StringBuffer sb = new StringBuffer(50);
        return sb.append(request.getMethod()).append(request.getServletPath()).append("/").toString();
    }

    private String getClientId() {
        return UUID.randomUUID().toString();
    }

}
