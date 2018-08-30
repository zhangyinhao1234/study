package com.github.zhangyinhao1234.study.mybatis.dao;

import java.util.List;

import com.github.zhangyinhao1234.study.mybatis.bean.UserExample;

public interface UserMapper {
    
     /**
      * 查询所有的用户信息
      * @return
      * @throws Exception
      */
    public List<UserExample> selectAllUser() throws Exception;
}