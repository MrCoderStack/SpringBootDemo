package com.mrcoder.sbmannotations.dao;
import com.mrcoder.sbmannotations.domain.Person;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface PersonDao {
    @Select("select * from person where id = #{id}")
    Person getPersonById(@Param("id") int id);

    @Select("select * from person")
    List<Person> getPersonList();

}
