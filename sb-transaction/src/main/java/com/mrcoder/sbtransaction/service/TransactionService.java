package com.mrcoder.sbtransaction.service;

import com.mrcoder.sbtransaction.mapper.StudentMapper;
import com.mrcoder.sbtransaction.mapper.TeacherMapper;
import com.mrcoder.sbtransaction.model.Student;
import com.mrcoder.sbtransaction.model.Teacher;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService {

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Transactional
    public void addStudentAndTeacher() {
        studentService.addStudentNoTrans();
        teacherService.addTeacherNoTrans();
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void addStudent() {
        Student student = new Student();
        student.setAge(20);
        student.setName("学生");
        studentMapper.insert(student);
        int i = 1 / 0;
    }

    public void addStudent2() {
        ((TransactionService) AopContext.currentProxy()).addStudent();
    }


}
