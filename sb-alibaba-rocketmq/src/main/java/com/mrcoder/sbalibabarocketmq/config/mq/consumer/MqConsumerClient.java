package com.mrcoder.sbalibabarocketmq.config.mq.consumer;

import com.aliyun.openservices.ons.api.bean.ConsumerBean;
import com.mrcoder.sbalibabarocketmq.config.mq.tcp.util.MqTcpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


/**
 * @Description: 订阅消费者
 */
@Component
public class MqConsumerClient {

//    @Autowired
//    private MqTcpProperties mqTcpProperties;

    @Autowired
    private MqMessageListener mqMessageListener;

//    @Autowired
//    private MqTcpTopicProperties mqTcpTopicProperties;

    @Autowired
    private MqTcpUtil mqTcpUtil;

//    //自定义消费者
//    @Bean(initMethod = "start", destroyMethod = "shutdown")
//    public ConsumerBean messageBuildConsumer() {
//        ConsumerBean consumerBean = mqTcpUtil.getConsumer();
//        //配置文件
//        Properties properties = mqTcpProperties.getMqProperties();
//        //消费者
//        properties.setProperty(PropertyKeyConst.GROUP_ID, mqTopicProperties.getGroupId());
//        //设置消费者线程数为20个（默认20）
//        properties.setProperty(PropertyKeyConst.ConsumeThreadNums, "20");
//        consumerBean.setProperties(properties);
//        //订阅消息
//        Map<Subscription, MessageListener> subscriptionTable = new HashMap<>();
//        // 广播订阅方式设置
//        properties.put(PropertyKeyConst.MessageModel, PropertyValueConst.BROADCASTING);
//        //订阅消息
//        Subscription smsSubscription = new Subscription();
//        smsSubscription.setTopic(mqTopicProperties.getTopic());
//        smsSubscription.setExpression(mqTopicProperties.getTag());
//        subscriptionTable.put(smsSubscription, mqMessageListener);
//        consumerBean.setSubscriptionTable(subscriptionTable);
//        return consumerBean;
//    }

    // 默认消费者
    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public ConsumerBean defaultConsumer() {
        ConsumerBean consumerBean = mqTcpUtil.getDefaultConsumer(mqMessageListener);
        return consumerBean;
    }

}

