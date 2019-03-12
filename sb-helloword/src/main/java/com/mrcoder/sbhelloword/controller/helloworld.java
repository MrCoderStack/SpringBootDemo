package com.mrcoder.sbhelloword.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloworld {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(){
        return "helloworld";
    }
}
