package com.mrcoder.sbmannotations.controller;

import com.mrcoder.sbmannotations.domain.Demo;
import com.mrcoder.sbmannotations.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
public class DemoController {
    @Autowired
    private DemoService demoService;

    @RequestMapping(value = "/person/{id}", method = RequestMethod.GET)
    public Demo getPersonById(@PathVariable int id) {
        return demoService.getPersonById(id);
    }

    @RequestMapping(value = "/person", method = RequestMethod.GET)
    public ArrayList<Demo> getPersonList() {
        return demoService.getPersonList();
    }
}