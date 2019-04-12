package com.mrcoder.sbmmultidbxml.controller;
import com.mrcoder.sbmmultidbxml.entity.Student;
import com.mrcoder.sbmmultidbxml.entity.Teacher;
import com.mrcoder.sbmmultidbxml.mapper.master.StudentMapper;
import com.mrcoder.sbmmultidbxml.mapper.slave.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class CurdController {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @RequestMapping("/students")
    public List<Student> getStudentList() {
        List<Student> studentList = studentMapper.getList();
        return studentList;
    }

    @RequestMapping("/teachers")
    public List<Teacher> getTeacherList() {
        List<Teacher> teacherList = teacherMapper.getList();
        return teacherList;
    }
}
