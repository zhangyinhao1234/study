package com.github.zhangyinhao1234.asm;

public class InterceptorExample implements Interceptor {


    @Override
    public void before() {
        System.out.println("run...........before");
    }
}
