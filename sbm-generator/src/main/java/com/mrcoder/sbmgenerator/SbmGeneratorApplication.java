package com.mrcoder.sbmgenerator;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mrcoder.sbmgenerator.mapper")
public class SbmGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbmGeneratorApplication.class, args);
    }

}
