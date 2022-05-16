package com.mrcoder.sbmannotations.service;

import com.mrcoder.sbmannotations.domain.Person;

import java.util.ArrayList;
import java.util.List;

public interface PersonService {
    Person getPersonById(int id);
    List<Person> getPersonList();
}
