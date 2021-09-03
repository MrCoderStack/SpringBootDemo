package com.mrcoder.sbspelannotations.aop.aspect;

import com.mrcoder.sbspelannotations.aop.annotation.SpelGetParm;
import com.mrcoder.sbspelannotations.util.SpelUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Description: spel+aop获取动态参数
 */
@Aspect
@Slf4j
@Component
public class SpelGetParmAop {

    @PostConstruct
    public void init() {
        log.info("SpelGetParm init ......");
    }

    /**
     * 拦截加了SpelGetParm注解的方法请求
     *
     * @param joinPoint
     * @param spelGetParm
     * @return
     * @throws Throwable
     */
    @Around("@annotation(spelGetParm)")
    public Object beforeInvoce(ProceedingJoinPoint joinPoint, SpelGetParm spelGetParm) throws Throwable {

        Object result = null;

        // 方法名
        String methodName = joinPoint.getSignature().getName();

        //获取动态参数
        String parm = SpelUtil.generateKeyBySpEL(spelGetParm.parm(), joinPoint);

        log.info("spel获取动态aop参数: {}", parm);

        try {
            log.info("执行目标方法: {} ==>>开始......", methodName);
            result = joinPoint.proceed();
            log.info("执行目标方法: {} ==>>结束......", methodName);

            // 返回通知
            log.info("目标方法 " + methodName + " 执行结果 " + result);
        } finally {

        }
        // 后置通知
        log.info("目标方法 " + methodName + " 结束");

        return result;
    }

}
