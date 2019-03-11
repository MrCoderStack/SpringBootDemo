package com.mrcoder.sbmgenerator.service;

import com.mrcoder.sbmgenerator.entity.Person;

import java.util.List;

public interface PersonService {
    int addPerson(Person person);
    List<Person> findAllPerson(int pageNum, int pageSize);
}
