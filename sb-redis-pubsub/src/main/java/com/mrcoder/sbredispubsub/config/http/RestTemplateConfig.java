package com.mrcoder.sbredispubsub.config.http;


import java.io.IOException;

import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import com.mrcoder.sbredispubsub.utils.RestTemplateUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;


/**
 * @Description: RestTemplate配置类
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());

        restTemplate.setErrorHandler(new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse response) throws IOException {
                // 返回true，即是不检查状态码及抛异常了
                return true;
            }

            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
            }
        });

        return restTemplate;
    }

    @Bean
    public HttpComponentsClientHttpRequestFactory clientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        /** 支持重定向配置 */
        factory.setHttpClient(HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy()).build());
        factory.setConnectTimeout(15000);
        factory.setReadTimeout(15000);
        return factory;
    }

    /**
     * 注入RestTemplateUtil工具类
     *
     * @return
     */
    @Bean
    public RestTemplateUtil restTemplateUtil() {
        return new RestTemplateUtil();
    }

}