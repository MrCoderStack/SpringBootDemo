package com.mrcoder.sbswagger2.controller;


import com.mrcoder.sbswagger2.model.Person;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;


@Api(value = "Api文档实例")
@RestController
public class Swagger2Controller {

    @ApiOperation(value="获取列表",tags={"获取列表信息"},notes="列表")
    @GetMapping("/getList")
    public int getList() {
        return 1;
    }

    @ApiOperation("详情")
    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "long", paramType = "query")
    @GetMapping("/getInfoById")
    public Long getInfoById(Long id) {
        return id;
    }

    @ApiOperation("查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "name", value = "名称", dataType = "string", paramType = "query", example = "张三")
    })
    @GetMapping("/queryList")
    public Long queryList(Long id, String name) {
        return id;
    }

    @ApiOperation("查询2")
    @GetMapping("/query")
    public Long query(@ApiParam(name = "id", value = "id", required = true) Long id, @ApiParam(name = "name", value = "名称", required = true) String name) {
        return id;
    }


    @ApiOperation("新增")
    @ApiParam(name = "Person对象", value = "传入json格式", required = true)
    @PostMapping("/addPerson")
    public Person addPerson(@RequestBody Person person) {
        return person;
    }


    @ApiIgnore()
    @ApiOperation("隐藏")
    @ApiParam(name = "Person对象", value = "传入json格式", required = true)
    @GetMapping("/hideFunc")
    public Person hideFunc(@RequestBody Person person) {
        return person;
    }
}
