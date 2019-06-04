package com.mrcoder.sbswagger2.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import springfox.documentation.annotations.ApiIgnore;


@ApiModel(value = "Person对象", description = "用户对象Person")
@Getter
@Setter
@Accessors(chain = true)
public class Person {
    private Long id;

    @ApiModelProperty(value = "名称", name = "name", example = "张三", required = true)
    //@NotBlank(message = "name不能为空")
    private String name;

    @ApiModelProperty(value = "年龄", name = "age")
    //@NotBlank(message = "age不能为空")
    private Integer age;

    @ApiModelProperty(value = "状态", name = "status")
    private Integer status;

}
