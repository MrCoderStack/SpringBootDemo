package com.mrcoder.sbmannotations.dao;
import com.mrcoder.sbmannotations.domain.Demo;
import org.apache.ibatis.annotations.*;
import java.util.ArrayList;

@Mapper
public interface DemoDao {
    @Select("select * from person where id = #{id}")
    // 返回 Map 结果集
    @Results({
            @Result(property = "id", column = "id"),
    })
    Demo getPersonById(@Param("id") int id);

    @Select("select * from person")
    ArrayList<Demo> getPersonList();

}