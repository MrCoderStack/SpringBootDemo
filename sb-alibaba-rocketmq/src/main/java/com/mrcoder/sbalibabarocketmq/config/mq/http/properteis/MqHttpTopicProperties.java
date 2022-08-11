package com.mrcoder.sbalibabarocketmq.config.mq.http.properteis;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 默认topic配置
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "aliyun.rocketmq.http")
public class MqHttpTopicProperties {
    private String topic;
    private String groupId;
    private String tag;
}
