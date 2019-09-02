package com.mrcoder.sbredisproducerconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SbRedisProducerConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbRedisProducerConsumerApplication.class, args);
    }

}
