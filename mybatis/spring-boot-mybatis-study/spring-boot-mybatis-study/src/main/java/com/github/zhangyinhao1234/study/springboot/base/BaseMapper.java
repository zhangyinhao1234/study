package com.github.zhangyinhao1234.study.springboot.base;


import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;


public abstract interface BaseMapper <T> extends Mapper<T>, MySqlMapper<T> {


}
