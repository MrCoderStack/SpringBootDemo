package com.mrcoder.sbredisannotations.controller;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CacheController {

    @RequestMapping(value = "/setcache/{id}", method = RequestMethod.GET)
    //动态设置参数id为缓存结果的key
    //Cacheable注解会先检查key存不存在，不存在就执行方法并缓存结果后返回
    @Cacheable(cacheNames = "cache", key = "#id")
    public String setCache(@PathVariable int id){
        return "set cache";
    }

    @RequestMapping(value = "/updatecache/{id}", method = RequestMethod.GET)
    //CachePut注解会不管key存不存在，都会更新缓存
    @CachePut(cacheNames = "cache", key = "#id")
    public String updateCache(@PathVariable int id){
        return "update cache";
    }

    @RequestMapping(value = "/deletecache/{id}", method = RequestMethod.GET)
    //CacheEvict注解会删除缓存
    @CacheEvict(cacheNames = "cache", key = "#id")
    public String deleteCache(@PathVariable int id){
        return "delete cache";
    }



}
