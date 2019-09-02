package com.mrcoder.sbredisproducerconsumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Scheduled(initialDelay = 5000, fixedDelay = 60 * 1000)
    public void consumer() {
        String message = redisTemplate.opsForList().rightPop("queue");
        System.out.println("-----------消费者------------>消费了:" + message);
    }
}
