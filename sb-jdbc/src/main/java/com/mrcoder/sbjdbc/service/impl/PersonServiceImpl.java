package com.mrcoder.sbjdbc.service.impl;

import com.mrcoder.sbjdbc.dao.PersonDao;
import com.mrcoder.sbjdbc.entity.Person;
import com.mrcoder.sbjdbc.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    PersonDao personDao;

    @Override
    public List<Person> getPersonList() {
        return personDao.getPersonList();
    }
}
