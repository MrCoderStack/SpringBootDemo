package com.mrcoder.sbalibabanacos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping(value = "getInfoById")
    public String getInfoById(@RequestParam(value = "id") Long Id) {
        return "sb-alibaba-nacos return :" + Id;
    }
    ;
}
