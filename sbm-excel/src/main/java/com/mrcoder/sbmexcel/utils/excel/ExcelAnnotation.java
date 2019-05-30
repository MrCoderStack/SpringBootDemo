package com.mrcoder.sbmexcel.utils.excel;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelAnnotation {

    /**
     * 列索引
     *
     * @return
     */
    public int columnIndex() default 0;

    /**
     * 列名
     *
     * @return
     */
    public String columnName() default "";

}
