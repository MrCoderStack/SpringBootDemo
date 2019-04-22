package com.mrcoder.sbmmultidbxmldruidatomikos.config.wrapper;

import com.google.common.base.CaseFormat;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.wrapper.MapWrapper;

import java.util.Map;

/**
 * @Description: 自定义wrapper处理spring boot + mybatis返回结果为map时的key值转换为驼峰
 */
public class CustomWrapper extends MapWrapper {

    public CustomWrapper(MetaObject metaObject, Map<String, Object> map) {
        super(metaObject, map);
    }

    @Override
    public String findProperty(String name, boolean useCamelCaseMapping) {
        if (useCamelCaseMapping) {
            return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, name);
        }
        return name;
    }
}
