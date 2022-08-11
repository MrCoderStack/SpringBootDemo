package com.mrcoder.test;

import com.alibaba.fastjson.JSONObject;
import com.mrcoder.sbalibabarocketmq.SbAlibabaRocketmqApplication;
import com.mrcoder.sbalibabarocketmq.config.mq.http.util.MqHttpUtil;
import com.mrcoder.sbalibabarocketmq.config.mq.tcp.util.MqTcpUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;


@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SbAlibabaRocketmqApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MqTest {

    @Autowired
    private MqTcpUtil mqTcpUtil;

    @Autowired
    private MqHttpUtil mqHttpUtil;


    @Test
    public void mqTcpTest() {
        // 自定义一条body内容
        JSONObject body = new JSONObject();
        UUID uuid = UUID.randomUUID();
        body.put("notice", "这是一条tcp通知类信息");
        //同步发送消息-不带返回值的（一般使用该方法）
        log.info(String.valueOf(mqTcpUtil.sendMsg("topic", "tag_dev", body.toJSONString(), String.valueOf(uuid))));
    }

    @Test
    public void mqHttpTest() {
        // 自定义一条body内容
        JSONObject body = new JSONObject();
        UUID uuid = UUID.randomUUID();
        body.put("notice", "这是一条http通知类信息");
        //同步发送消息-不带返回值的（一般使用该方法）
        log.info(String.valueOf(mqHttpUtil.sendMsg("topic", "tag_dev", body.toJSONString(), String.valueOf(uuid))));
    }
}
