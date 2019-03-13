package com.mrcoder.sbjdbc.controller;

import com.mrcoder.sbjdbc.entity.Person;
import com.mrcoder.sbjdbc.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {
    @Autowired
    PersonService personService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Person> getPersonList() {
        return personService.getPersonList();
    }

}
