package com.github.zhangyinhao1234.study.proxy;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.alibaba.fastjson.JSON;

public class DaoProxy<T> implements InvocationHandler,Serializable{

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        
        System.out.println("method:"+method.getName()+";args:"+JSON.toJSONString(args));
        
        DefaultDaoMehtod defaultDaoMehtod = new DefaultDaoMehtod();
        
        return defaultDaoMehtod.execute(args);
    }

}
