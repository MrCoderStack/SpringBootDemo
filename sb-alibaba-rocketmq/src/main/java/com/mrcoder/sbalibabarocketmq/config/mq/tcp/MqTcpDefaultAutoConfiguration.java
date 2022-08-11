package com.mrcoder.sbalibabarocketmq.config.mq.tcp;

import com.aliyun.openservices.ons.api.bean.ConsumerBean;
import com.aliyun.openservices.ons.api.bean.ProducerBean;
import com.mrcoder.sbalibabarocketmq.config.mq.tcp.properteis.MqTcpProperties;
import com.mrcoder.sbalibabarocketmq.config.mq.tcp.properteis.MqTcpTopicProperties;
import com.mrcoder.sbalibabarocketmq.config.mq.tcp.util.MqTcpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * 阿里云rocketMq服务自动配置类
 * 业务系统要使用阿里云rocketMq服务服务，需要在配置文件中增加如下配置
 * 【
 * aliyun.rocketmq.tcp.enable=true
 * aliyun.rocketmq.tcp.accessKeyId=
 * aliyun.rocketmq.tcp.accessKeySecret=
 * aliyun.rocketmq.tcp.nameSrvAddr=
 * aliyun.rocketmq.tcp.default.topic=
 * aliyun.rocketmq.tcp.default.groupId=
 * aliyun.rocketmq.tcp.default.tag=
 */
@Slf4j
@Configuration
@EnableConfigurationProperties({MqTcpProperties.class, MqTcpTopicProperties.class})
@ConditionalOnClass({ProducerBean.class, ConsumerBean.class})
@ConditionalOnProperty(prefix = "aliyun.rocketmq.tcp", value = "enable", havingValue = "true")
public class MqTcpDefaultAutoConfiguration {

    @Autowired
    private MqTcpProperties mqTcpProperties;

    @PostConstruct
    public void init() {
        log.info("[Auto Config] MqTcpDefaultAutoConfiguration loading......");
    }

    @Bean(initMethod = "start", destroyMethod = "shutdown")
    @ConditionalOnMissingBean
    public ProducerBean buildProducer() {
        ProducerBean producer = new ProducerBean();
        producer.setProperties(mqTcpProperties.getMqProperties());
        return producer;
    }

    @Bean
    public MqTcpUtil mqTcpUtil() {
        return new MqTcpUtil();
    }

}
