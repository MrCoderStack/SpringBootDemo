package com.mrcoder.sbjpa.controller;

import com.mrcoder.sbjpa.entity.Person;
import com.mrcoder.sbjpa.service.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {
    @Autowired
    private PersonRepository personRepository;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Person> getPersonList(){
        return personRepository.findAll();
    }



}
