package com.mrcoder.sbmmultidbxmldruidatomikos.mapper.slave;


import com.mrcoder.sbmmultidbxmldruidatomikos.entity.slave.Teacher;


import java.util.List;

public interface TeacherMapper {
    //    @Select("SELECT * FROM teacher")
    List<Teacher> getList();

    //    @Select("SELECT * FROM teacher WHERE id = #{id}")
    Teacher getById(Long id);

    //    @Insert("INSERT INTO teacher(age,course,name) VALUES(#{age}, #{course}, #{name})")
    void insert(Teacher teacher);

    //    @Update("UPDATE teacher SET name=#{name},age=#{age}, course=#{course} WHERE id =#{id}")
    void update(Teacher teacher);

    //    @Delete("DELETE FROM teacher WHERE id =#{id}")
    void delete(Long id);
}
