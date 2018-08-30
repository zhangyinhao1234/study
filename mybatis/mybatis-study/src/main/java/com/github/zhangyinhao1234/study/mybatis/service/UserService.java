package com.github.zhangyinhao1234.study.mybatis.service;

import java.lang.reflect.Proxy;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.github.zhangyinhao1234.study.mybatis.action.DBTools;
import com.github.zhangyinhao1234.study.mybatis.bean.UserExample;
import com.github.zhangyinhao1234.study.mybatis.dao.UserMapper;

public class UserService {

    public static void main(String[] args) throws Exception {
        /**
         * 1：SqlSessionFactoryBuilder 通过 XMLConfigBuilder 加载解析配置文件  返回 Configuration  创建 DefaultSqlSessionFactory
         * 2：DefaultSqlSessionFactory#openSession()： 创建事务 通过Configuration 创建  Executor（CachingExecutor） 然后 返回 DefaultSqlSession
         * 3：DefaultSqlSession#getMapper(): MapperRegistry#getMapper()通过mapper的class获取 MapperProxyFactory 调用newInstance方法 
         * 创建 MapperProxy 对象 然后使用 Proxy.newProxyInstance 进行动态代理处理。 MapperProxy 的 invoke 对Mapper的所有方法进行了代理因此实现了mapper的映射
         * 4：MapperProxy#invoke()中：mapperMethod.execute(sqlSession, args) 解析参数执行相应的查询方法，获取sql使用 sqlSession执行相应的代码
         * 5：在 SimpleExecutor 中最后进行数据底层的数据处理
         */
        UserMapper mapper = DBTools.getSession().getMapper(UserMapper.class);
        List<UserExample> selectAllUser = mapper.selectAllUser();
        System.out.println(JSON.toJSONString(selectAllUser));
        

        
    }
}
