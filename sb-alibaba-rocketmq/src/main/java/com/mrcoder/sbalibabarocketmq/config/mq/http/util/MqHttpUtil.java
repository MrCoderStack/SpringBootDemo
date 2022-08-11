package com.mrcoder.sbalibabarocketmq.config.mq.http.util;

import com.aliyun.mq.http.MQClient;
import com.aliyun.mq.http.MQConsumer;
import com.aliyun.mq.http.MQProducer;
import com.aliyun.mq.http.model.TopicMessage;
import com.mrcoder.sbalibabarocketmq.config.mq.http.properteis.MqHttpProperties;
import com.mrcoder.sbalibabarocketmq.config.mq.http.properteis.MqHttpTopicProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * @Description: mq工具类
 */
@Slf4j
public class MqHttpUtil {

    @Autowired
    private MqHttpProperties mqHttpProperties;

    @Autowired
    private MqHttpTopicProperties mqHttpTopicProperties;

    private MQProducer producer;

    private MQConsumer consumer;

    private MQClient mqClient;

    @PostConstruct
    public void init() {
        log.info("[Init] http producer init");
        mqClient = new MQClient(
                // 设置HTTP协议客户端接入点，进入消息队列RocketMQ版控制台实例详情页面的接入点区域查看。
                mqHttpProperties.getNameSrvAddr(),
                // AccessKey ID，阿里云身份验证，在阿里云RAM控制台创建。
                mqHttpProperties.getAccessKeyId(),
                // AccessKey Secret，阿里云身份验证，在阿里云RAM控制台创建。
                mqHttpProperties.getAccessKeySecret()
        );
    }

    /**
     * 同步发送消息
     *
     * @param topic       topic名
     * @param msgTag      标签，可用于消息小分类标注
     * @param messageBody 消息body内容，生产者自定义内容
     * @param msgKey      消息key值，建议设置全局唯一，可不传，不影响消息投递
     * @return success:SendResult or error:null
     */
    public TopicMessage sendMsg(String topic, String msgTag, String messageBody, String msgKey) {
        producer = mqClient.getProducer(topic);
        TopicMessage msg = new TopicMessage(
                messageBody.getBytes(),
                msgTag);
        msg.setMessageId(msgKey);

        try {
            //可靠同步发送
            TopicMessage sendResult = producer.publishMessage(msg);
            //获取发送结果，不抛异常即发送成功
            assert sendResult != null;
            success(topic, msg);
            return sendResult;

        } catch (Exception e) {
            error(topic, msg, e);
            return null;
        }
    }

    public MQClient getMqClient() {
        return mqClient;
    }

    public MQConsumer getDefaultConsumer() {
        consumer = mqClient.getConsumer(mqHttpTopicProperties.getTopic(), mqHttpTopicProperties.getGroupId(), mqHttpTopicProperties.getTag());
        return consumer;
    }


    /**
     * 成功日志打印
     *
     * @param msg
     */
    private void success(String topic, TopicMessage msg) {
        log.info("发送MQ消息成功 -- Topic:{} ,msgId:{} , tag:{}, body:{}"
                , topic, msg.getMessageId(), msg.getMessageTag(), msg.getMessageBodyString());
    }

    /**
     * 异常日志打印
     *
     * @param msg
     * @param e
     */
    private void error(String topic, TopicMessage msg, Exception e) {
        log.error("发送MQ消息失败-- Topic:{}, msgId:{}, tag:{}, body:{}"
                , topic, msg.getMessageId(), msg.getMessageTag(), msg.getMessageBodyString());
        log.error("errorMsg", e);
    }


}
