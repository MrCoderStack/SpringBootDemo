package com.mrcoder.sbmgenerator.controller;


import com.mrcoder.sbmgenerator.entity.Person;
import com.mrcoder.sbmgenerator.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PersonController {
    @Autowired
    private PersonService personService;

    @ResponseBody
    @RequestMapping(value = "/all/{pageNum}/{pageSize}", produces = {"application/json;charset=UTF-8"})
    public Object findAllPerson(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize){

        return personService.findAllPerson(pageNum,pageSize);
    }



}
