package com.mrcoder.sbmmultidbxmldruidatomikos.service;


import com.mrcoder.sbmmultidbxmldruidatomikos.utils.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;


/**
 * redis 测试业务层
 */
@Service
public class RedisService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisUtil redisUtil;//内部封装了 RedisTemplate<String, Object>

    /**
     * 测试存储 String 类型数据
     */
    public void setValue(String key, String string) {
        redisUtil.set(key, string);
    }

    /**
     * 测试读取 String 类型数据
     */
    public Object getValue(String key) {
        boolean flag = redisUtil.hasKey(key);
        Object rs = null;
        if (flag) {
            rs = redisUtil.get(key);
        }
        return rs;
    }

}
