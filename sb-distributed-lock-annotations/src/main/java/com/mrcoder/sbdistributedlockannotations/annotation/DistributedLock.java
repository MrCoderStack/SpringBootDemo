package com.mrcoder.sbdistributedlockannotations.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: 分布式锁自定义注解
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DistributedLock {

    /**
     * 锁超时时间【单位秒，默认5秒】
     *
     * @return
     */
    long expireTime() default 5;

    /**
     * 分布式缓存Key【SpEL表达式】
     *
     * @return
     */
    String lockKey();

    /**
     * 获取锁失败默认提示消息
     *
     * @return
     */
    String message() default "获取分布式锁失败";
}
