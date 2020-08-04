package com.mrcoder.sbalibabanacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@SpringBootApplication
public class SbAlibabaNacosApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbAlibabaNacosApplication.class, args);
    }

}
