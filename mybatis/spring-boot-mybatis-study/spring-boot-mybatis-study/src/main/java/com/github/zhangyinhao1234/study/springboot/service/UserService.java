package com.github.zhangyinhao1234.study.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.zhangyinhao1234.study.springboot.dao.UserMapper;
import com.github.zhangyinhao1234.study.springboot.pojo.UserExample;

@Service
public class UserService {
    @Autowired
    private UserMapper usermapper;

    public List<UserExample> selectAllUser() {
        return usermapper.selectAllUser();
    }

}
