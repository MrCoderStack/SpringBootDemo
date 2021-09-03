package com.mrcoder.sbnorepeatsubmit.util;

import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Description: redis工具类
 */
public class RedisCacheUtil {

    private RedisTemplate<String, Object> redisTemplate;

    public RedisCacheUtil(RedisTemplate<String, Object> redisTemplate) {
        super();
        this.redisTemplate = redisTemplate;
    }

    /**
     * 添加缓存，永不过期
     *
     * @param key
     * @param value
     */
    public void set(String key, Serializable value) {
        this.redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 添加缓存，带有效时间
     *
     * @param key      缓存key
     * @param value    缓存value
     * @param timeOut  缓存失效时间
     * @param timeUnit 时间单位
     */
    public void set(String key, Serializable value, long timeOut, TimeUnit timeUnit) {
        this.redisTemplate.opsForValue().set(key, value, timeOut, timeUnit);
    }

    /**
     * 添加缓存，存在返回false；如果不存在，就存入reidis返回true
     *
     * @param key
     * @param value
     * @return
     */
    public boolean setNx(String key, Serializable value) {
        return this.redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    public boolean setNx(String key, Serializable value, long timeOut, TimeUnit timeUnit) {
        return this.redisTemplate.opsForValue().setIfAbsent(key, value, timeOut, timeUnit);
    }

    /**
     * 获取缓存
     *
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        return (T) this.redisTemplate.opsForValue().get(key);
    }

    /**
     * 刷新缓存有效时间
     *
     * @param key
     * @param timeOut
     * @param timeUnit
     */
    public void expireKey(String key, long timeOut, TimeUnit timeUnit) {
        this.redisTemplate.expire(key, timeOut, timeUnit);
    }

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    public boolean exists(String key) {
        return this.redisTemplate.hasKey(key);
    }

    /**
     * 批量删除缓存
     *
     * @param key
     */
    public void delKey(String... keys) {
        for (String key : keys) {
            this.redisTemplate.delete(key);
        }
    }

    /**
     * 根据通配符删除缓存
     *
     * @param pattern
     */
    public void delKeyByPattern(String... patterns) {
        for (String pattern : patterns) {
            Set<String> keys = redisTemplate.keys(pattern);
            if (keys.size() > 0) {
                redisTemplate.delete(keys);
            }
        }
    }

    /**
     * 存储list，永久有效
     *
     * @param key
     * @param list
     */
    public <T> void setList(String key, List<T> list) {
        for (T temp : list) {
            this.redisTemplate.opsForList().rightPush(key, temp);
        }
    }

    /**
     * 保存list，带有效时间
     *
     * @param key
     * @param list
     * @param timeOut
     * @param timeUnit
     */
    public <T> void setListExpire(String key, List<T> list, long timeOut, TimeUnit timeUnit) {
        for (T temp : list) {
            this.redisTemplate.opsForList().rightPush(key, temp);
        }
        this.redisTemplate.expire(key, timeOut, timeUnit);
    }

    /**
     * 获取list
     *
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> getList(String key) {
        if (!exists(key)) {
            return null;
        }
        List<T> list = new ArrayList<T>();
        List<Object> objList = this.redisTemplate.opsForList().range(key, 0, -1);
        for (Object object : objList) {
            list.add((T) object);
        }
        return list;
    }

    /**
     * 获取对应缓存的有效时间
     *
     * @param key
     * @return
     */
    public Long getExpire(String key) {
        return this.redisTemplate.getExpire(key);
    }

    /**
     * 获取缓存-rightPop
     *
     * @param key
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T getRghtPop(String key) {
        if (!exists(key)) {
            return null;
        }
        return (T) this.redisTemplate.opsForList().rightPop(key);
    }

    /**
     * 添加缓存，永不过期-leftPush
     *
     * @param key
     * @param object
     */
    public void setLeftPush(String key, Object object) {
        this.redisTemplate.opsForList().leftPush(key, object);
    }

}
