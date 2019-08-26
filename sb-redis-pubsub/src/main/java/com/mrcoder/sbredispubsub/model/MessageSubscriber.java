package com.mrcoder.sbredispubsub.model;

import com.mrcoder.sbredispubsub.api.MessagePubApi;
import com.mrcoder.sbredispubsub.utils.FastJsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description: 消息订阅类
 */
@Component
public class MessageSubscriber {
    private static final Logger logger = LoggerFactory.getLogger(MessageSubscriber.class);

    @Autowired(required = false)
    private MessagePubApi messagePubApi;

    public void onMessage(SimpleMessage simpleMessage, String pattern) {
        logger.info("topic {} received {}", pattern, FastJsonUtil.javaToJsonSnakeCase(simpleMessage));
        messagePubApi.pubWxNotice(simpleMessage);
    }
}

