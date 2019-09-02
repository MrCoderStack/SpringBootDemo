package com.mrcoder.sbredisproducerconsumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ProducerService {
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Scheduled(initialDelay = 5000, fixedDelay = 30 * 1000)
    public void producer() {
        String message = "消息 " + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        System.out.println("-----------生产者------------>生产了:" + message);
        redisTemplate.opsForList().leftPush("queue", message);
    }
}
