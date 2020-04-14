package com.github.zhangyinhao1234.study.agent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * 测试一个agent，监测一个class
 * @author yinhao.zhang
 *
 */
public class Transformer implements ClassFileTransformer{

	public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
			ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
		// TODO Auto-generated method stub
		System.out.println("classname：-----------------:"+className);
		if(!"HelloTrans".equals(className)){
			return null;
		}






		return null;
	}




}
