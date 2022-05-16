package com.mrcoder.sbmannotations.controller;

import com.mrcoder.sbmannotations.domain.Person;
import com.mrcoder.sbmannotations.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {
    @Autowired
    private PersonService demoService;

    @RequestMapping(value = "/person/{id}", method = RequestMethod.GET)
    public Person getPersonById(@PathVariable int id) {
        Person person = demoService.getPersonById(id);
        return person;
    }

    @RequestMapping(value = "/person", method = RequestMethod.GET)
    public List<Person> getPersonList() {
        List<Person> list = demoService.getPersonList();
        return list;
    }
}
