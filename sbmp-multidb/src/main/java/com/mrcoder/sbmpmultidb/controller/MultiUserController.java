package com.mrcoder.sbmpmultidb.controller;


import com.mrcoder.sbmpmultidb.entity.MultiUser;
import com.mrcoder.sbmpmultidb.service.IMultiUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mrcoder
 * @since 2019-03-07
 */
@Controller
@RestController
@RequestMapping("/sbmpmultidb/multi-user")
public class MultiUserController {
    @Autowired
    private IMultiUserService userService;

    @RequestMapping("/id")
    public MultiUser id(){
        MultiUser user = userService.getById(1);
        return user;
    }

    @RequestMapping("/list")
    public List<MultiUser> list() {
        List<MultiUser> userList = userService.list();
        return userList;
    }

    @RequestMapping("add")
    public String add(){
        MultiUser userMaster = MultiUser.builder().name("主库添加").age(20).build();
        userService.addUser(userMaster);
        return "add success";
    }

    @RequestMapping("update")
    public String update(){

        MultiUser userMaster = MultiUser.builder().id(1L).name("主库添加222").age(20).build();
        userService.updateById(userMaster);
        return "update success";
    }
}
