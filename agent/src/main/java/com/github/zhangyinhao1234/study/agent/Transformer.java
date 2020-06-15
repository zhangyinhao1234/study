package com.github.zhangyinhao1234.study.agent;

import com.github.zhangyinhao1234.asm.ASMMethod;
import com.github.zhangyinhao1234.javassist.JavassistMethod;
import javassist.*;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * 测试一个agent，监测一个class
 *
 * @author yinhao.zhang
 */
public class Transformer implements ClassFileTransformer {

    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        // TODO Auto-generated method stub
//        System.out.println("classname：-----------------:" + className);
        if (!className.contains("HelloTrans")) {
            return null;
        }
        System.out.println("加载"+className+"。。。的字节码增强");

        try {
            return new JavassistMethod().reloadClass();
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("return null");
        return null;
//        return new ASMMethod().reloadClass(classfileBuffer);
    }






}
