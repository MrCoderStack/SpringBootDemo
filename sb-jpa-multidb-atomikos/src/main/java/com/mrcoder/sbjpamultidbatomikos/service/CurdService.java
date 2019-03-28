package com.mrcoder.sbjpamultidbatomikos.service;

import com.mrcoder.sbjpamultidbatomikos.entity.master.Student;
import com.mrcoder.sbjpamultidbatomikos.entity.master.StudentDao;
import com.mrcoder.sbjpamultidbatomikos.entity.slave.Teacher;
import com.mrcoder.sbjpamultidbatomikos.entity.slave.TeacherDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CurdService {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private TeacherDao teacherDao;

    @Transactional
    public void add(int code) {
        Student s1 = new Student();
        s1.setAge(10);
        s1.setGrade(10);
        s1.setName("s1");
        studentDao.save(s1);

        Teacher t1 = new Teacher();
        t1.setAge(10);
        t1.setName("t1");
        t1.setCourse(10);
        teacherDao.save(t1);

        int result = 1/code;
    }
}
