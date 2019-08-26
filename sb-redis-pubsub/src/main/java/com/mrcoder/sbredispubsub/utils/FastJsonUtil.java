package com.mrcoder.sbredispubsub.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.serializer.SerializeConfig;

/**
 * @Description: Fastjson工具类
 */
public class FastJsonUtil {

    private static SerializeConfig snakeCaseConfig = null;

    static {
        snakeCaseConfig = new SerializeConfig();
        snakeCaseConfig.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
    }

    /**
     * JavaBean转JSON,驼峰转下划线
     *
     * @param object
     * @return
     */
    public static String javaToJsonSnakeCase(Object object) {
        return JSON.toJSONString(object, snakeCaseConfig);

    }
}
