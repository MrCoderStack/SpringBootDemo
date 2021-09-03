package com.mrcoder.sbminterceptor.mapper;

import com.mrcoder.sbminterceptor.model.Student;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;
import java.util.ArrayList;

public interface StudentMapper extends Mapper<Student>, InsertListMapper<Student>{
    @Select("select * from student where id = #{id}")
    // 返回 Map 结果集
    @Results({
            @Result(property = "id", column = "id"),
    })
    Student getStudentById(@Param("id") int id);

    @Select("select * from student")
    ArrayList<Student> getStudentList();

//    @Insert({ "insert into student(name, age, grade, phone) values(#{name}, #{age}, #{grade}, #{phone})" })
//    int insert(Student student);
}