package com.mrcoder.sbminterceptor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisConfig {

    @Bean
    public ParammeterInterceptor parammeterInterceptor() {
        return new ParammeterInterceptor();
    }

    @Bean
    public ResultInterceptor resultInterceptor(){
        return new ResultInterceptor();
    }
}
