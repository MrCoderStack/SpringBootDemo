package com.mrcoder.sbmmultidbxml.mapper.master;
import com.mrcoder.sbmmultidbxml.entity.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import java.util.List;

public interface StudentMapper {
//    @Select("SELECT * FROM student")
    List<Student> getList();

//    @Select("SELECT * FROM student WHERE id = #{id}")
    Student getById(int id);

//    @Insert("INSERT INTO student(age,grade,name) VALUES(#{age}, #{grade}, #{name})")
    void insert(Student student);

//    @Update("UPDATE student SET name=#{name},age=#{age}, grade=#{grade} WHERE id =#{id}")
    void update(Student student);

//    @Delete("DELETE FROM student WHERE id =#{id}")
    void delete(int id);
}
