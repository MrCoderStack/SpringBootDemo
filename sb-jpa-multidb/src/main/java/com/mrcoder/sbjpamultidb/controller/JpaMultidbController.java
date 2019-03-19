package com.mrcoder.sbjpamultidb.controller;

import com.mrcoder.sbjpamultidb.entity.master.StudentDao;
import com.mrcoder.sbjpamultidb.entity.slave.TeacherDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JpaMultidbController {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private TeacherDao teacherDao;

    @RequestMapping("/list")
    public void list() {
        System.out.println(studentDao.findAll());
        System.out.println(teacherDao.findAll());
    }
}
