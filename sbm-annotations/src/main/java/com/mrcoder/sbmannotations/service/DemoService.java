package com.mrcoder.sbmannotations.service;

import com.mrcoder.sbmannotations.domain.Demo;

import java.util.ArrayList;

public interface DemoService {
    Demo getPersonById(int id);
    ArrayList<Demo> getPersonList();
}