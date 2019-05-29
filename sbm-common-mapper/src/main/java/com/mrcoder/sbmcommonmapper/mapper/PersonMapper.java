package com.mrcoder.sbmcommonmapper.mapper;

import com.mrcoder.sbmcommonmapper.model.Person;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.ExampleMapper;
import tk.mybatis.mapper.common.Mapper;


//继承通用mapper
public interface PersonMapper extends Mapper<Person>, InsertListMapper<Person>, ExampleMapper<Person> {
}
