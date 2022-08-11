package com.mrcoder.sbalibabarocketmq.config.mq.http;

import com.mrcoder.sbalibabarocketmq.config.mq.http.util.MqHttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * 阿里云rocketMq服务自动配置类
 * 业务系统要使用阿里云rocketMq服务服务，需要在配置文件中增加如下配置
 * 【
 * aliyun.rocketmq.http.enable=true
 * aliyun.rocketmq.http.accessKeyId=
 * aliyun.rocketmq.http.accessKeySecret=
 * aliyun.rocketmq.http.nameSrvAddr=
 * aliyun.rocketmq.http.default.topic=
 * aliyun.rocketmq.http.default.groupId=
 * aliyun.rocketmq.http.default.tag=
 */
@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "aliyun.rocketmq.http", value = "enable", havingValue = "true")
public class MqHttpDefaultAutoConfiguration {

    @PostConstruct
    public void init() {
        log.info("[Auto Config] MqHttpDefaultAutoConfiguration loading......");
    }

    @Bean
    public MqHttpUtil mqHttpUtil() {
        return new MqHttpUtil();
    }

}
