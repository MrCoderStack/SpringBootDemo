package com.mrcoder.sbxssfilter.controller;


import com.mrcoder.sbxssfilter.model.Demo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class XssController {

    //正常转义
    @PostMapping("xssFilter")
    public String xssFilter(String name, String info) {
        return name + "---" + info;
    }

    @PostMapping("modelXssFilter")
    public Demo modelXssFilter(@RequestBody Demo demo) {
        System.out.println(demo.getName());
        System.out.println(demo.getInfo());
        return demo;
    }

    //不转义
    @PostMapping("open/xssFilter")
    public String openXssFilter(String name) {
        return name;
    }

    //不转义2
    @PostMapping("open2/xssFilter")
    public String open2XssFilter(String name) {
        return name;
    }
}
