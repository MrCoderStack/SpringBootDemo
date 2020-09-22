package com.mrcoder.sbxssfilter.controller;


import com.mrcoder.sbxssfilter.model.People;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class XssController {

    //键值对
    @PostMapping("xssFilter")
    public String xssFilter(String name, String info) {
        log.error(name + "---" + info);
        return name + "---" + info;
    }
    //实体
    @PostMapping("modelXssFilter")
    public People modelXssFilter(@RequestBody People people) {
        log.error(people.getName() + "---" + people.getInfo());
        return people;
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
