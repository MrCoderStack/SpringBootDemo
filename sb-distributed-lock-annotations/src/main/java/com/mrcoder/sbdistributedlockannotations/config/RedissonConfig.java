package com.mrcoder.sbdistributedlockannotations.config;

import org.redisson.Redisson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private String port;

    @Value("${spring.redis.password}")
    private String password;


    @Bean
    @ConditionalOnMissingBean(name = "redisson")
    public Redisson redisson() {
        org.redisson.config.Config config = new org.redisson.config.Config();
        config.useSingleServer().setAddress("redis://" + host + ":" + port);
        return (Redisson) Redisson.create(config);
    }
}
