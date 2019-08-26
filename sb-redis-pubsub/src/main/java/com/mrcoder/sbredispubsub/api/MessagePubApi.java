package com.mrcoder.sbredispubsub.api;

import com.alibaba.fastjson.JSON;
import com.mrcoder.sbredispubsub.model.ResponseInfo;
import com.mrcoder.sbredispubsub.model.SimpleMessage;
import com.mrcoder.sbredispubsub.utils.RestTemplateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


/**
 * @Description: 推送API
 */


@Component
public class MessagePubApi {
    private static final Logger logger = LoggerFactory.getLogger(MessagePubApi.class);

    @Autowired(required = false)
    private RestTemplateUtil restTemplateUtil;

    @Value("https://www.testWXapi.com")
    private String BUSSINESS_WX_ROBOT_URL;

    //企业微信机器人推送
    public int pubWxNotice(SimpleMessage simpleMessage) {
        Map Msg = new HashMap();
        Msg.put("msgtype", "markdown");
        Map text = new HashMap();
        text.put("content", simpleMessage.getContent());
        Msg.put("markdown", text);
        String requestParam = JSON.toJSONString(Msg);
        ResponseInfo response = null;
        System.out.println(requestParam);
        try {
            response = this.restTemplateUtil.syncPostJson(BUSSINESS_WX_ROBOT_URL, requestParam, ResponseInfo.class);
            if (response == null) {
                logger.error("企业微信机器人推送失败, [requestParam: {}, response: {}]", requestParam, JSON.toJSONString(response));
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("企业微信机器人推送异常, [requestParam: {}, response: {}]", requestParam, e.getMessage());
            return -1;
        }

        logger.info("企业微信机器人推送成功, [requestParam: {}, response: {}]", requestParam, JSON.toJSONString(response));

        return 0;
    }
}
