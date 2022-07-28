package com.mrcoder.sbdistributedlockannotations.util;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.support.AopUtils;

/**
 * @Description: 拓展AOP工具类
 */
public class AopUtil extends AopUtils {

    /**
     * 判断环绕通知目标方法返回值是否为Void
     *
     * @param joinPoint
     * @return
     */
    public static boolean isReturnVoid(ProceedingJoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        Class returnType = ((MethodSignature) signature).getReturnType();
        return returnType.equals(Void.TYPE);
    }
}
