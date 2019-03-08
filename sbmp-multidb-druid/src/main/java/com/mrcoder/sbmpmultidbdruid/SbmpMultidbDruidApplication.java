package com.mrcoder.sbmpmultidbdruid;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mrcoder.sbmpmultidbdruid.mapper")
public class SbmpMultidbDruidApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbmpMultidbDruidApplication.class, args);
    }

}
