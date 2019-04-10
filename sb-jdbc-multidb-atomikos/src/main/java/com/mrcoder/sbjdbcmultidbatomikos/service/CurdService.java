package com.mrcoder.sbjdbcmultidbatomikos.service;

import com.mrcoder.sbjdbcmultidbatomikos.dao.StudentDao;
import com.mrcoder.sbjdbcmultidbatomikos.dao.TeacherDao;
import com.mrcoder.sbjdbcmultidbatomikos.entity.Student;
import com.mrcoder.sbjdbcmultidbatomikos.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(transactionManager = "transactionManager", propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = {Exception.class})
public class CurdService {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private TeacherDao teacherDao;

    public void add(int code) {
        Student s1 = new Student();
        s1.setAge(1);
        s1.setGrade(1);
        s1.setName("s1");
        studentDao.save(s1);

        Teacher t1 = new Teacher();
        t1.setAge(1);
        t1.setName("t1");
        t1.setCourse(1);
        teacherDao.save(t1);

        int result = 1 / code;
    }

}
