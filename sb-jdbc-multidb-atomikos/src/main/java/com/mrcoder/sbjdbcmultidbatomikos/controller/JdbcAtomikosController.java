package com.mrcoder.sbjdbcmultidbatomikos.controller;


import com.mrcoder.sbjdbcmultidbatomikos.dao.StudentDao;
import com.mrcoder.sbjdbcmultidbatomikos.dao.TeacherDao;
import com.mrcoder.sbjdbcmultidbatomikos.service.CurdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JdbcAtomikosController {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private CurdService curdService;

    @RequestMapping("/commit")
    public void add() {
        curdService.add(1);
    }

    @RequestMapping("/rollback")
    public void rollback() {
        curdService.add(0);
    }

    @RequestMapping("/list")
    public void list() {
        System.out.println(studentDao.getList());
        System.out.println(teacherDao.getList());
    }


}
