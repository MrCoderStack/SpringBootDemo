package com.mrcoder.sbnorepeatsubmit.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: 防止重复提交自定义注解
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface NoRepeatSubmit {

	String lockKey() default "";

	// 设置请求锁定时间【默认10秒】
	int lockTime() default 10;

}
