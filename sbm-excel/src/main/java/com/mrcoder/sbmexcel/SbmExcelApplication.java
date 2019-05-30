package com.mrcoder.sbmexcel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;


@MapperScan("com.mrcoder.sbmexcel.mapper")
@SpringBootApplication
public class SbmExcelApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbmExcelApplication.class, args);
    }

}
