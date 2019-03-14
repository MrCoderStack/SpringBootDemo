package com.mrcoder.sbredisannotations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SbRedisAnnotationsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbRedisAnnotationsApplication.class, args);
    }

}
