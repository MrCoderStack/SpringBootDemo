package com.mrcoder.sbalibabarocketmq.config.mq.tcp.properteis;

import com.aliyun.openservices.ons.api.PropertyKeyConst;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @Description: mq默认配置类
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "aliyun.rocketmq.tcp")
public class MqTcpProperties {

    //AccessKey ID，阿里云身份验证，在阿里云RAM控制台创建
    private String accessKeyId;

    //AccessKey Secret，阿里云身份验证，在阿里云RAM控制台创建
    private String accessKeySecret;

    //实例TCP协议接入地址（内网）
    private String nameSrvAddr;

    //发送超时时间: 3s
    private String sendMsgTimeoutMillis = "3000";

    //线程数目：默认20
    private String consumeThreadNums = "20";

    //订阅方式: 集群
    private String messageModel = "CLUSTERING";


    public Properties getMqProperties() {
        Properties properties = new Properties();
        properties.setProperty(PropertyKeyConst.AccessKey, this.accessKeyId);
        properties.setProperty(PropertyKeyConst.SecretKey, this.accessKeySecret);
        properties.setProperty(PropertyKeyConst.NAMESRV_ADDR, this.nameSrvAddr);
        // 设置发送超时时间，单位毫秒
        properties.setProperty(PropertyKeyConst.SendMsgTimeoutMillis, this.sendMsgTimeoutMillis);
        // 设置消费者线程数为20个（默认20）
        properties.setProperty(PropertyKeyConst.ConsumeThreadNums, this.consumeThreadNums);
        // 订阅方式设置
        properties.put(PropertyKeyConst.MessageModel, this.messageModel);
        return properties;
    }
}


