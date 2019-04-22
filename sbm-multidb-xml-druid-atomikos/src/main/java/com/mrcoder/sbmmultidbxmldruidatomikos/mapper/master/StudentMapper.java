package com.mrcoder.sbmmultidbxmldruidatomikos.mapper.master;

import com.mrcoder.sbmmultidbxmldruidatomikos.entity.master.Student;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StudentMapper {
    //    @Select("SELECT * FROM student")
    List<Student> getList();

    //    @Select("SELECT * FROM student WHERE id = #{id}")
    Student getById(Long id);

    //    @Insert("INSERT INTO student(age,grade,name) VALUES(#{age}, #{grade}, #{name})")
    Integer insert(Student student);

    //    @Update("UPDATE student SET name=#{name},age=#{age}, grade=#{grade} WHERE id =#{id}")
    Integer update(Student student);

    //    @Delete("DELETE FROM student WHERE id =#{id}")
    Integer delete(Long id);


    //注解方式，本项目不建议使用
    @Select("SELECT * FROM student")
    List<Student> getListByAnno();
}
