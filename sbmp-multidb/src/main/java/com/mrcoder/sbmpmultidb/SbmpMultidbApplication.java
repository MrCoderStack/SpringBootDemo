package com.mrcoder.sbmpmultidb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mrcoder.sbmpmultidb.mapper")
public class SbmpMultidbApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbmpMultidbApplication.class, args);
    }

}
