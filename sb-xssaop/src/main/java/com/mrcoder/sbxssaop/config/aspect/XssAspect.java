package com.mrcoder.sbxssaop.config.aspect;

import cn.hutool.core.util.EscapeUtil;
import cn.hutool.core.util.StrUtil;
import com.mrcoder.sbxssaop.config.annotation.XssField;
import com.mrcoder.sbxssaop.config.annotation.XssMethod;
import com.mrcoder.sbxssaop.config.annotation.XssParam;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @Description: AOP防XSS攻击
 */
@Aspect
@Slf4j
@Component
public class XssAspect {
    @PostConstruct
    public void init() {
        log.info("XssAspect init ......");
    }

    @Around("@annotation(xssMethod)")
    public Object around(ProceedingJoinPoint joinPoint, XssMethod xssMethod) throws Throwable {
        Object[] args = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 获取所有参数上的注解（一个参数可能对应多个注解，因此获取到的是一个二维数组。）
        Annotation[][] paramsAnnotations = signature.getMethod().getParameterAnnotations();
        // 遍历一维数组，获取每个参数对应的注解数组
        for (int i = 0; i < paramsAnnotations.length; i++) {
            Annotation[] paramAnnotations = paramsAnnotations[i];
            // 遍历每个参数的注解数组
            for (Annotation annotation : paramAnnotations) {
                // 如果参数需要预防XSS攻击
                if (annotation instanceof XssParam) {
                    // 如果是String类型，将其进行格式化
                    // 否则获取该类型的所有字段，对String类型的字段进行格式化
                    if (args[i] instanceof String && StrUtil.isNotEmpty((String) args[i])) {
                        args[i] = format((String) args[i]);
                    } else {
                        Class clazz = args[i].getClass();
                        // 获取类的所有字段
                        Field[] fields = clazz.getDeclaredFields();
                        for (Field field : fields) {
                            // 如果字段上有@PreventXSSField注解
                            if (field.getDeclaredAnnotation(XssField.class) != null) {
                                // 如果是字段不可访问，设置临时可访问
                                if (!field.isAccessible()) {
                                    field.setAccessible(true);
                                }
                                // 如果字段是字符串类型则进行格式化
                                Object fieldValue = field.get(args[i]);
                                if (fieldValue instanceof String && StrUtil.isNotEmpty((String) fieldValue)) {
                                    field.set(args[i], format((String) fieldValue));
                                }
                            }
                        }
                    }
                }
            }
        }
        // 将参数覆盖到到原方法
        Object proceed = joinPoint.proceed(args);
        return proceed;
    }

    /**
     * 对需要防范的字符串进行格式化
     */
    public String format(String xssStr) {
        return EscapeUtil.escape(xssStr);
    }
}
