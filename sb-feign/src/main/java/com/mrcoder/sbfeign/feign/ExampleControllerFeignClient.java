package com.mrcoder.sbfeign.feign;

import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "example-service", url = "http://127.0.0.1:8081/", fallbackFactory = ExampleControllerFeignClient.ExampleControllerFeignClientFallbackFactory.class)
public interface ExampleControllerFeignClient {

    @GetMapping(value = "getInfoById")
    String getInfoById(@RequestParam(value = "id") Long Id);

    /**
     * 服务降级内部类
     */
    @Component
    class ExampleControllerFeignClientFallbackFactory implements FallbackFactory<ExampleControllerFeignClient> {

        private Logger logger = LoggerFactory.getLogger(ExampleControllerFeignClientFallbackFactory.class);

        @Override
        public ExampleControllerFeignClient create(Throwable cause) {
            return new ExampleControllerFeignClient() {
                @Override
                public String getInfoById(Long signingLogId) {
                    logger.error("跨服务调用失败， 原因是:" + cause.getMessage());
                    return "失败， 原因是:" + cause.getMessage();
                }
            };
        }
    }
}
