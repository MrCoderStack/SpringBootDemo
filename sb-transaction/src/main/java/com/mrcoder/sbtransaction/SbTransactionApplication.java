package com.mrcoder.sbtransaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.mrcoder.sbtransaction.mapper")
@EnableAspectJAutoProxy(exposeProxy = true)
public class SbTransactionApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbTransactionApplication.class, args);
    }

}
