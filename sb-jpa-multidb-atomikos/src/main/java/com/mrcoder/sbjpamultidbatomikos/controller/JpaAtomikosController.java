package com.mrcoder.sbjpamultidbatomikos.controller;


import com.mrcoder.sbjpamultidbatomikos.entity.master.StudentDao;
import com.mrcoder.sbjpamultidbatomikos.service.CurdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JpaAtomikosController {

    @Autowired
    private CurdService curdService;

    @Autowired
    private StudentDao studentDao;

    @RequestMapping("/add")
    public void add() {
        curdService.add(1);
    }

    @RequestMapping("/test")
    public void test() {
        curdService.add(0);
    }

    @RequestMapping("/list")
    public void list() {
        System.out.println(studentDao.findAll());

    }
}
