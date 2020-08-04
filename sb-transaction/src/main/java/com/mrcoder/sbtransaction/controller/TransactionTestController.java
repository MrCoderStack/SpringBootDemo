package com.mrcoder.sbtransaction.controller;

import com.mrcoder.sbtransaction.service.StudentService;
import com.mrcoder.sbtransaction.service.TeacherService;
import com.mrcoder.sbtransaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionTestController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/test")
    public void test() {
        transactionService.addStudent();
    }

    @GetMapping("/test2")
    public void test2() {
        transactionService.addStudent2();
    }
}
