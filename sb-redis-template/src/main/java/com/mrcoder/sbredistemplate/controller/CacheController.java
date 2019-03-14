package com.mrcoder.sbredistemplate.controller;

import com.mrcoder.sbredistemplate.entity.Person;
import com.mrcoder.sbredistemplate.service.RedisService;
import com.mrcoder.sbredistemplate.util.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
public class CacheController {
    @Autowired
    private RedisTemplate redisTemplate;
    @Resource
    private RedisService redisService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    //字符串类型操作
    @Resource
    private ValueOperations<String, Object> valueOperations;

    //hash散列操作
    @Autowired
    private HashOperations<String, String, Object> hashOperations;

    //列表类型操作
    @Autowired
    private ListOperations<String, Object> listOperations;

    //集合类型操作
    @Autowired
    private SetOperations<String, Object> setOperations;

    //有序集合类型操作
    @Autowired
    private ZSetOperations<String, Object> zSetOperations;


    @RequestMapping(value = "/setcache", method = RequestMethod.GET)
    public void setCache() {
        Person person = new Person();
        person.setId(1);
        person.setName("p1");
        person.setAge(20);
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        String key = RedisKeyUtil.getKey(Person.Table, "name", person.getName());
        redisService.expireKey(key, 20, TimeUnit.SECONDS);
        operations.set(key, person);
        Person p1 = (Person) operations.get(key);
        System.out.println(p1);
        operations.getOperations().delete(key);
        Person p2 = (Person) operations.get(key);
        System.out.println(p2);
    }

}
