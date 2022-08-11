package com.mrcoder.sbalibabarocketmq.config.mq.tcp.util;

import cn.hutool.core.util.ObjectUtil;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.SendResult;
import com.aliyun.openservices.ons.api.bean.ConsumerBean;
import com.aliyun.openservices.ons.api.bean.ProducerBean;
import com.aliyun.openservices.ons.api.bean.Subscription;

import com.mrcoder.sbalibabarocketmq.config.mq.tcp.properteis.MqTcpProperties;
import com.mrcoder.sbalibabarocketmq.config.mq.tcp.properteis.MqTcpTopicProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Description: mq工具类
 */
@Slf4j
public class MqTcpUtil {

    @Autowired
    private ProducerBean producer;

    @Autowired
    private MqTcpProperties mqTcpProperties;

    @Autowired
    private MqTcpTopicProperties mqTcpTopicProperties;

    private ConsumerBean consumerBean;

    /**
     * 同步发送消息 - 配置默认topic
     *
     * @param msgTag      标签，可用于消息小分类标注
     * @param messageBody 消息body内容，生产者自定义内容
     * @param msgKey      消息key值，建议设置全局唯一，可不传，不影响消息投递
     * @return success:SendResult or error:null
     */
    public SendResult sendMsg(String msgTag, String messageBody, String msgKey) {
        Message msg = new Message(mqTcpTopicProperties.getTopic(), msgTag, msgKey, messageBody.getBytes());
        return this.send(msg, Boolean.FALSE);
    }

    /**
     * 同步发送消息 - 配置默认topic - 重试次数
     *
     * @param msgTag      标签，可用于消息小分类标注
     * @param messageBody 消息body内容，生产者自定义内容
     * @param msgKey      消息key值，建议设置全局唯一，可不传，不影响消息投递
     * @param retryTimes  重试次数，注意实际请求次数为 retryTimes + 1
     * @return success:SendResult or error:null
     */
    public SendResult sendMsg(String msgTag, String messageBody, String msgKey, Integer retryTimes) {
        Message msg = new Message(mqTcpTopicProperties.getTopic(), msgTag, msgKey, messageBody.getBytes());
        SendResult result = this.send(msg, Boolean.FALSE);
        if (ObjectUtil.isNotEmpty(result) || retryTimes == 0) {
            return result;
        }
        return this.sendMsg(msgTag, messageBody, msgKey, --retryTimes);
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
    public SendResult sendMsg(String topic, String msgTag, String messageBody, String msgKey) {
        Message msg = new Message(topic, msgTag, msgKey, messageBody.getBytes());
        return this.send(msg, Boolean.FALSE);
    }

    /**
     * 同步发送单向消息
     *
     * @param topic       topic名
     * @param msgTag      标签，可用于消息小分类标注
     * @param messageBody 消息body内容，生产者自定义内容
     * @param msgKey      消息key值，建议设置全局唯一，可不传，不影响消息投递
     */
    public void sendOneWayMsg(String topic, String msgTag, String messageBody, String msgKey) {
        Message msg = new Message(topic, msgTag, msgKey, messageBody.getBytes());
        this.send(msg, Boolean.TRUE);
    }


    /**
     * 发送普通消息
     *
     * @param msg      消息
     * @param isOneWay 是否单向发送
     */
    private SendResult send(Message msg, Boolean isOneWay) {
        try {
            if (isOneWay) {
                //由于在 oneway 方式发送消息时没有请求应答处理，一旦出现消息发送失败，则会因为没有重试而导致数据丢失。
                //若数据不可丢，建议选用同步或异步发送方式。
                producer.sendOneway(msg);
                success(msg, "单向消息MsgId不返回");
                return null;
            } else {
                //可靠同步发送
                SendResult sendResult = producer.send(msg);
                //获取发送结果，不抛异常即发送成功
                assert sendResult != null;
                success(msg, sendResult.getMessageId());
                return sendResult;
            }
        } catch (Exception e) {
            error(msg, e);
            return null;
        }
    }

    /**
     * 成功日志打印
     *
     * @param msg
     * @param messageId
     */
    private void success(Message msg, String messageId) {
        log.info("发送MQ消息成功 -- Topic:{} ,msgId:{} , Key:{}, tag:{}, body:{}"
                , msg.getTopic(), messageId, msg.getKey(), msg.getTag(), new String(msg.getBody()));
    }

    /**
     * 异常日志打印
     *
     * @param msg
     * @param e
     */
    private void error(Message msg, Exception e) {
        log.error("发送MQ消息失败-- Topic:{}, Key:{}, tag:{}, body:{}"
                , msg.getTopic(), msg.getKey(), msg.getTag(), new String(msg.getBody()));
        log.error("errorMsg", e);
    }


    @PostConstruct
    public void init() {
        log.info("[Init] tcp consumerBean init");
        consumerBean = new ConsumerBean();
    }

    public ConsumerBean getConsumer() {
        return consumerBean;
    }

    public ConsumerBean getDefaultConsumer(MessageListener messageListener) {
        //配置文件
        Properties properties = mqTcpProperties.getMqProperties();
        //消费者
        properties.setProperty(PropertyKeyConst.GROUP_ID, mqTcpTopicProperties.getGroupId());
        //设置消费者线程数为20个（默认20）
        properties.setProperty(PropertyKeyConst.ConsumeThreadNums, mqTcpProperties.getConsumeThreadNums());
        // 广播订阅方式设置
        properties.put(PropertyKeyConst.MessageModel, mqTcpProperties.getMessageModel());
        consumerBean.setProperties(properties);

        //订阅消息
        Map<Subscription, MessageListener> subscriptionTable = new HashMap<>();
        //订阅消息
        Subscription smsSubscription = new Subscription();
        smsSubscription.setTopic(mqTcpTopicProperties.getTopic());
        smsSubscription.setExpression(mqTcpTopicProperties.getTag());
        subscriptionTable.put(smsSubscription, messageListener);
        consumerBean.setSubscriptionTable(subscriptionTable);
        return consumerBean;
    }
}
