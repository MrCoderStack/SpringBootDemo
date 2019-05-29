package com.mrcoder.sbmcommonmapper.controller;


import com.mrcoder.sbmcommonmapper.mapper.PersonMapper;
import com.mrcoder.sbmcommonmapper.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CommonMapperController {

    @Autowired
    private PersonMapper personMapper;


    @GetMapping("/getList")
    public List<Person> getList() {
        //通用mapper的获取列表方法
        return personMapper.selectAll();
    }

    @GetMapping("/getById/{id}")
    public Person getById(@PathVariable("id") Integer id) {
        //通用mapper的根据主键获取指定记录方法
        return personMapper.selectByPrimaryKey(id);
    }


    @GetMapping("/insert")
    public int insert() {
        Person person = new Person();
        person.setAge(10);
        person.setName("p1");
        //插入方法
        return personMapper.insert(person);
    }

    @GetMapping("/update")
    public int update() {
        Person person = new Person();
        person.setId(1);
        person.setAge(10);
        person.setName("p1-update");
        //更新方法
        return personMapper.updateByPrimaryKeySelective(person);
    }

    @GetMapping("/batchInsert")
    public int batchInsert() {
        Person p1 = new Person();
        p1.setAge(10);
        p1.setName("p2");

        Person p2 = new Person();
        p2.setAge(10);
        p2.setName("p3");
        List<Person> personList = new ArrayList<Person>();
        personList.add(p1);
        personList.add(p2);
        //批量插入方法
        return personMapper.insertList(personList);
    }

    @GetMapping("/query")
    public List<Person> query() {
        Example con = new Example(Person.class);
        Example.Criteria criteria = con.createCriteria();
        criteria.andEqualTo("age", 10);
        //复杂条件查询方法
        return personMapper.selectByExample(con);
    }
}
