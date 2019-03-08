package com.mrcoder.sbmannotations.service.impl;

import com.mrcoder.sbmannotations.dao.DemoDao;
import com.mrcoder.sbmannotations.domain.Demo;
import com.mrcoder.sbmannotations.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DemoServiceImpl implements DemoService {
    @Autowired
    private DemoDao demoDao;

    public Demo getPersonById(int id) {
        return demoDao.getPersonById(id);
    }

    public ArrayList<Demo> getPersonList() {
        return demoDao.getPersonList();
    }

}