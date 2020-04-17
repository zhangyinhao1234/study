package com.github.zhangyinhao1234.asm;

public final class InterceptorFactory {

    public static Interceptor getInterceptor(int i){
        System.out.println("getInterceptor...." + i);
        return new InterceptorExample();
    }
}
