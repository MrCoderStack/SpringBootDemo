package com.mrcoder.sbxssaop.controller;

import com.mrcoder.sbxssaop.config.annotation.XssMethod;
import com.mrcoder.sbxssaop.config.annotation.XssParam;
import com.mrcoder.sbxssaop.model.People;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class XssController {

    @PostMapping("xssFilter")
    @XssMethod
    public String xssFilter(@XssParam String name, String info) {
        log.error(name + "-----" + info);
        return name + "---" + info;
    }

    @XssMethod
    @PostMapping("modelXssFilter")
    public People modelXssFilter(@RequestBody @XssParam People people) {
        log.error(people.getName() + "-----" + people.getInfo());
        return people;
    }
}
