package com.github.zhangyinhao1234.study.springboot.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.github.zhangyinhao1234.study.springboot.base.BaseMapper;
import com.github.zhangyinhao1234.study.springboot.pojo.UserExample;
@Repository
public interface UserMapper extends BaseMapper<UserExample> {

    /**
     * 查询所有的用户信息
     * 
     * @return
     * @throws Exception
     */
    List<UserExample> selectAllUser();

}