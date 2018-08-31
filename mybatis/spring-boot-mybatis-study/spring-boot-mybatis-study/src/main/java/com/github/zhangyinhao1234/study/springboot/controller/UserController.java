package com.github.zhangyinhao1234.study.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.zhangyinhao1234.study.springboot.dao.UserMapper;
import com.github.zhangyinhao1234.study.springboot.pojo.UserExample;
import com.github.zhangyinhao1234.study.springboot.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper usermapper;

    @RequestMapping("/test/listallUser")
    public List<UserExample> listallUser() {
        return this.userService.selectAllUser();
    }

    @RequestMapping("/test/get/{id}")
    public UserExample listallUser(@PathVariable("id") Long id) {
        return this.usermapper.selectByPrimaryKey(id);
    }
}
