package com.github.zhangyinhao1234.test;

import com.github.zhangyinhao1234.asm.Interceptor;
import com.github.zhangyinhao1234.asm.InterceptorFactory;

public class HelloWord {
    public void hi() {
        Interceptor interceptor = InterceptorFactory.getInterceptor(1);
        interceptor.before();
        System.out.println("hi.........HelloTrans");
    }
}
