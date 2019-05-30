package com.mrcoder.sbmexcel.model;

import com.mrcoder.sbmexcel.utils.excel.ExcelAnnotation;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Setter
@Getter
@Table(name = "excel")
@Accessors(chain = true)
public class Excel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ExcelAnnotation(columnIndex = 1, columnName = "姓名")
    private String name;

    @ExcelAnnotation(columnIndex = 2, columnName = "年龄")
    private Integer age;

    @ExcelAnnotation(columnIndex = 3, columnName = "身高")
    private Double height;

    @ExcelAnnotation(columnIndex = 4, columnName = "体重")
    private Double weight;

    @ExcelAnnotation(columnIndex = 5, columnName = "学历")
    private String edu;

    private Date createTime;

    private Date updateTime;

    private Integer status;

}