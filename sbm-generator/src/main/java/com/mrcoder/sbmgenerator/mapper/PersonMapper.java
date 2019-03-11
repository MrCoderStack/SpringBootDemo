package com.mrcoder.sbmgenerator.mapper;

import com.mrcoder.sbmgenerator.entity.Person;

import java.util.List;

public interface PersonMapper {
    int insert(Person record);

    int insertSelective(Person record);

    List<Person> selectAllPerson();

}