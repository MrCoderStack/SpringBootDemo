package com.mrcoder.sbmmultidbannotations.mapper.master;
import com.mrcoder.sbmmultidbannotations.entity.Student;
import org.apache.ibatis.annotations.*;
import java.util.List;

public interface StudentMapper {
    @Select("SELECT * FROM student")
    List<Student> getList();

    @Select("SELECT * FROM student WHERE id = #{id}")
    Student getById(int id);

    @Insert("INSERT INTO student(age,grade,name) VALUES(#{age}, #{grade}, #{name})")
    void insert(Student student);

    @Update("UPDATE student SET name=#{name},age=#{age}, grade=#{grade} WHERE id =#{id}")
    void update(Student student);

    @Delete("DELETE FROM student WHERE id =#{id}")
    void delete(int id);
}
