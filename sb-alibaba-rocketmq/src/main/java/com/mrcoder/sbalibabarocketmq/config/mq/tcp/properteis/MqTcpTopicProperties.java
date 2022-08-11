package com.mrcoder.sbalibabarocketmq.config.mq.tcp.properteis;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: TODO
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "aliyun.rocketmq.tcp")
public class MqTcpTopicProperties {
    private String topic;
    private String groupId;
    private String tag;
}
