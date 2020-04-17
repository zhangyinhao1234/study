package com.github.zhangyinhao1234.study.agent;

import javassist.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class JavassistTest {


    public static void main(String[] args) throws NotFoundException, CannotCompileException, IOException {
        System.out.println("JavassistMethod....进行增强");
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.get("com.github.zhangyinhao1234.test.HelloTrans");
        CtMethod ctMethod = ctClass.getDeclaredMethod("hi");
        ctMethod.insertBefore("com.github.zhangyinhao1234.asm.Interceptor interceptor = com.github.zhangyinhao1234.asm.InterceptorFactory.getInterceptor(1);");
        byte[] bytes = ctClass.toBytecode();
        System.out.println("JavassistMethod....进行增强完成");

    }


    private static void savefile( byte[] bytes)throws NotFoundException, CannotCompileException, IOException {
        String dst="/Users/yinhao.zhang/soft/"+ UUID.randomUUID() +".class";
        FileOutputStream fos = new FileOutputStream(dst);
        fos.write(bytes);
    }


}
