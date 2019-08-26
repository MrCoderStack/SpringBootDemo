package com.mrcoder.sbredispubsub.controller;

import com.mrcoder.sbredispubsub.model.ResponseInfo;
import com.mrcoder.sbredispubsub.utils.FileUtil;
import com.mrcoder.sbredispubsub.utils.RedisPubSubUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;


@CrossOrigin
@RestController
public class RedisPubSubController {

    @Autowired
    private RedisPubSubUtil redisPubSubUtil;

    @Autowired
    private FileUtil fileUtil;

    @GetMapping("redisPubSub")
    public ResponseInfo redisPubSub() throws IOException {
        redisPubSubUtil.publish("echo", fileUtil.readFileByLine("classpath:version/version.md"));
        return ResponseInfo.success("ok");
    }

}
