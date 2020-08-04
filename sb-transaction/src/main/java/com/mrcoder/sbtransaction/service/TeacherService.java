package com.mrcoder.sbtransaction.service;

import com.mrcoder.sbtransaction.mapper.TeacherMapper;
import com.mrcoder.sbtransaction.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;


    @Transactional(propagation = Propagation.REQUIRED)
    public void addTeacherWithTrans() {
        Teacher teacher = new Teacher();
        teacher.setAge(40);
        teacher.setName("老师");
        teacherMapper.insert(teacher);
        int i = 1 / 0;
    }

    public void addTeacherNoTrans() {
        Teacher teacher = new Teacher();
        teacher.setAge(40);
        teacher.setName("老师");
        teacherMapper.insert(teacher);
        int i = 1 / 0;
    }
}
