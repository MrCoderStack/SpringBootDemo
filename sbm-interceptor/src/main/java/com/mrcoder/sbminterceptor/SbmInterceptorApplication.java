package com.mrcoder.sbminterceptor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan({ "com.mrcoder.sbminterceptor.mapper"})
public class SbmInterceptorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbmInterceptorApplication.class, args);
	}

}
