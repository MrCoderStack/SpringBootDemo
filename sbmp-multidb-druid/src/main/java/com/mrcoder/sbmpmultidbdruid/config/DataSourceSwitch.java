package com.mrcoder.sbmpmultidbdruid.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface DataSourceSwitch {
    DBTypeEnum value() default DBTypeEnum.db1;
}