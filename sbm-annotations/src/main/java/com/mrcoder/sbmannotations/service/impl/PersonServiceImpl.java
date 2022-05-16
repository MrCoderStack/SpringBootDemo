package com.mrcoder.sbmannotations.service.impl;

import com.mrcoder.sbmannotations.dao.PersonDao;
import com.mrcoder.sbmannotations.domain.Person;
import com.mrcoder.sbmannotations.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonDao personDao;

    @Override
    public Person getPersonById(int id) {
        Person demo = personDao.getPersonById(id);
        return demo;
    }

    @Override
    public List<Person> getPersonList() {
        return personDao.getPersonList();
    }

}
