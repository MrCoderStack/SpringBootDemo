package com.mrcoder.sbminterceptor.controller;

import com.mrcoder.sbminterceptor.mapper.StudentMapper;
import com.mrcoder.sbminterceptor.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class DemoController {
    @Autowired
    private StudentMapper studentMapper;

    @RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
    public Student getPersonById(@PathVariable int id) {
        Student student = new Student();
        student.setAge(1);
        student.setName("1");
        student.setGrade(1);
        student.setPhone("15118011222");
        studentMapper.insert(student);
        return student;
    }

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public ArrayList<Student> getPersonList() {
        return studentMapper.getStudentList();
    }


}