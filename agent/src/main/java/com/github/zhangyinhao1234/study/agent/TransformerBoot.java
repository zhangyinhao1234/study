package com.github.zhangyinhao1234.study.agent;

import java.io.File;
import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarFile;

public class TransformerBoot {

    public static void premain(String agentArgs, Instrumentation inst)
            throws ClassNotFoundException, UnmodifiableClassException {
        List<JarFile> jarFiles = new ArrayList<JarFile>();
        jarFiles.add(openJarFile("/Users/yinhao.zhang/.m2/repository/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar"));
        jarFiles.add(openJarFile("/Users/yinhao.zhang/.m2/repository/org/ow2/asm/asm/7.3.1/asm-7.3.1.jar"));
        jarFiles.add(openJarFile("/Users/yinhao.zhang/.m2/repository/org/ow2/asm/asm-tree/7.3.1/asm-tree-7.3.1.jar"));
        String aa = "/Users/yinhao.zhang/.m2/repository/org/javassist/javassist/3.25.0-GA/javassist-3.25.0-GA.jar";
        jarFiles.add(openJarFile(aa));
        //inst加载jar文件
        for(JarFile jarFile : jarFiles){
            inst.appendToBootstrapClassLoaderSearch(jarFile);
        }
        //添加需要监测的类
        inst.addTransformer(new Transformer());
    }


    public static void main(String[] args) {
        System.out.println("main.........");
    }

    private static JarFile openJarFile(String filePath) {
        if (filePath == null) {
            throw new NullPointerException("filePath must not be null");
        }

        final File file = new File(filePath);
        if (!file.exists()) {
            throw new IllegalArgumentException(file + " not found");
        }
        if (file.isDirectory()) {
            throw new IllegalArgumentException(file + " is directory");
        }
        if (!(file.isFile())) {
            throw new IllegalArgumentException(file + " not file");
        }
        if (!file.canRead()) {
            throw new IllegalArgumentException(file + " can read");
        }
        try {
            return new JarFile(file);
        } catch (IOException e) {
            throw new IllegalStateException(file + " create fail Caused by:" + e.getMessage(), e);
        }
    }

}
