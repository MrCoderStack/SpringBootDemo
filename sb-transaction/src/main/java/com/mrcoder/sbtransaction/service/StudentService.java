package com.mrcoder.sbtransaction.service;

import com.mrcoder.sbtransaction.mapper.StudentMapper;
import com.mrcoder.sbtransaction.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private TeacherService teacherService;


    //测试二：addStudent无事务，addTeacher有事务
    //结果：addStudent成功，addTeacher失败
    public void addStudentAndTeacher2() {
        Student student = new Student();
        student.setAge(20);
        student.setName("学生");
        studentMapper.insert(student);
        teacherService.addTeacherWithTrans();
    }

    //测试一：addStudent有事务，addTeacher无事务
    //结果：无论异常在哪里，两条数据均无法插入
    @Transactional(propagation = Propagation.REQUIRED)
    public void addStudentAndTeacher() {
        Student student = new Student();
        student.setAge(20);
        student.setName("学生");
        studentMapper.insert(student);
        teacherService.addTeacherNoTrans();
    }


    public void addStudentNoTrans() {
        Student student = new Student();
        student.setAge(20);
        student.setName("学生");
        studentMapper.insert(student);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addStudentWithTrans() {
        Student student = new Student();
        student.setAge(22);
        student.setName("学生");
        studentMapper.insert(student);
    }

}
