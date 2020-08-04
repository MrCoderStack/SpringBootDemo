package com.mrcoder.sbfeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SbFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbFeignApplication.class, args);
    }

}
