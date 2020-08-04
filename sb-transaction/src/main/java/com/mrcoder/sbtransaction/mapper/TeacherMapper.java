package com.mrcoder.sbtransaction.mapper;

import com.mrcoder.sbtransaction.model.Teacher;
import tk.mybatis.mapper.common.ExampleMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

//继承通用mapper
public interface TeacherMapper extends Mapper<Teacher>, InsertListMapper<Teacher>, ExampleMapper<Teacher> {
}
