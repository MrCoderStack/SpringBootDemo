package com.mrcoder.sbmexcel.mapper;
import com.mrcoder.sbmexcel.model.Excel;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.ExampleMapper;
import tk.mybatis.mapper.common.Mapper;

public interface ExcelMapper extends Mapper<Excel>, InsertListMapper<Excel>, ExampleMapper<Excel> {


}