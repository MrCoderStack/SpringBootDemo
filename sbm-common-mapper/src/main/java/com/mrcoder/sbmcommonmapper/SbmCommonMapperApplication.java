package com.mrcoder.sbmcommonmapper;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;


//扫描mapper类
@MapperScan("com.mrcoder.sbmcommonmapper.mapper")
@SpringBootApplication
public class SbmCommonMapperApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbmCommonMapperApplication.class, args);
    }

}
