package com.mrcoder.sbmgenerator.service.impl;

import com.github.pagehelper.PageHelper;
import com.mrcoder.sbmgenerator.entity.Person;
import com.mrcoder.sbmgenerator.mapper.PersonMapper;
import com.mrcoder.sbmgenerator.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "personService")
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonMapper personMapper;//这里会报错，但是并不会影响

    @Override
    public int addPerson(Person person) {
        return personMapper.insert(person);
    }

    /*
     * 这个方法中用到了我们开头配置依赖的分页插件pagehelper
     * 很简单，只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可；
     * pageNum 开始页数
     * pageSize 每页显示的数据条数
     * */
    @Override
    public List<Person> findAllPerson(int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(pageNum, pageSize);
        return personMapper.selectAllPerson();
    }


    //无分页
    @Override
    public List<Person> findAllPersonNoPage() {
        return personMapper.selectAllPerson();
    }
}
