package com.mrcoder.sbjdbcmultidb.controller;

import com.mrcoder.sbjdbcmultidb.entity.Student;
import com.mrcoder.sbjdbcmultidb.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JdbcMultidbController {


    //master
    @Autowired
    @Qualifier("masterJdbcTemplate")
    protected JdbcTemplate masterTempleate;

    //slave
    @Autowired
    @Qualifier("slaveJdbcTemplate")
    protected JdbcTemplate slaveTempleate;

    @RequestMapping(value = "/list")
    public void list(){
        String studentSql = "select * from student";
        RowMapper<Student> studentRowMapper = new BeanPropertyRowMapper<>(Student.class);
        List<Student> studentList = masterTempleate.query(studentSql, studentRowMapper);

        String teacherSql = "select * from teacher";
        RowMapper<Teacher> teacherRowMapper = new BeanPropertyRowMapper<>(Teacher.class);
        List<Teacher> teacherList = slaveTempleate.query(teacherSql, teacherRowMapper);

        System.out.println(studentList);
        System.out.println(teacherList);

    }
}
