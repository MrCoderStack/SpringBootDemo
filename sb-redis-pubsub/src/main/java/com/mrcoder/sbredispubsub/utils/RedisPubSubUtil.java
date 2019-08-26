package com.mrcoder.sbredispubsub.utils;

import com.mrcoder.sbredispubsub.model.SimpleMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;

import java.util.Date;

/**
 * @Description: Redis发布订阅
 */
public class RedisPubSubUtil {

    private static final Logger logger = LoggerFactory.getLogger(RedisPubSubUtil.class);

    private RedisTemplate<String, Object> redisTemplate;

    public RedisPubSubUtil(RedisTemplate<String, Object> redisTemplate) {
        super();
        this.redisTemplate = redisTemplate;
    }

    public void publish(String publisher, String content) {
        logger.info("message send {} by {}", content, publisher);
        SimpleMessage simpleMessage = new SimpleMessage();
        simpleMessage.setContent(content);
        simpleMessage.setPublisher(publisher);
        simpleMessage.setCreateTime(new Date());
        ChannelTopic channelTopic = new ChannelTopic("redis.pubsub.msg");
        redisTemplate.convertAndSend(channelTopic.getTopic(), simpleMessage);
    }

    public void publish(String publisher, String type, String content, String Channel) {
        logger.info("message send {} by {}", content, publisher);
        SimpleMessage simpleMessage = new SimpleMessage();
        simpleMessage.setContent(content);
        simpleMessage.setPublisher(publisher);
        simpleMessage.setCreateTime(new Date());
        ChannelTopic channelTopic = new ChannelTopic(Channel);
        redisTemplate.convertAndSend(channelTopic.getTopic(), simpleMessage);

    }
}
