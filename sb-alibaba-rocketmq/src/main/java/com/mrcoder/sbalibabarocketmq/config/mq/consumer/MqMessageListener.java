package com.mrcoder.sbalibabarocketmq.config.mq.consumer;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 普通（默认同步）MQ消息监听消费
 */
@Slf4j
@Component
public class MqMessageListener implements MessageListener {

    @Override
    public Action consume(Message message, ConsumeContext context) {
        log.info("接收到MQ详细信息：{}", message);
        log.info("解析MQ-Body自定义内容：{}", new String(message.getBody()));
        try {
            //do something..
            return Action.CommitMessage;
        } catch (Exception e) {
            log.error("消费MQ消息失败，msgId:" + message.getMsgID() + "，ExceptionMsg：" + e.getMessage());
            return Action.ReconsumeLater;
        }
    }

}
