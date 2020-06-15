package com.github.zhangyinhao1234.javassist;

import com.github.zhangyinhao1234.asm.Util;
import javassist.*;

import java.io.IOException;

public class JavassistMethod {

    public byte[] reloadClass() throws NotFoundException, CannotCompileException, IOException {
        System.out.println("JavassistMethod....进行增强");
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.get("com.github.zhangyinhao1234.test.HelloTrans");
        CtMethod ctMethod = ctClass.getDeclaredMethod("hi");
        ctMethod.insertBefore("com.github.zhangyinhao1234.asm.Interceptor interceptor = com.github.zhangyinhao1234.asm.InterceptorFactory.getInterceptor(1);" +
                "interceptor.before();");



        ctMethod.insertAfter("com.github.zhangyinhao1234.asm.Interceptor interceptor = com.github.zhangyinhao1234.asm.InterceptorFactory.getInterceptor(1);" +
                "interceptor.after();");

//        ctMethod.insertAfter(  "interceptor.after();");

        byte[] bytes = ctClass.toBytecode();
//        Util.saveclass(bytes);
        System.out.println("JavassistMethod....进行增强完成");
        return bytes;

    }
}
