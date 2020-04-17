package com.github.zhangyinhao1234.test;

import com.github.zhangyinhao1234.asm.ShadowSocks;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.Method;
import org.objectweb.asm.tree.*;

import java.io.*;
import java.util.List;
import java.util.UUID;

import static org.objectweb.asm.Opcodes.*;
import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;


public class ASMTest {



    private static final String SHADOWSOCKS_DESCRIPTER = Type.getDescriptor(ShadowSocks.class);
    private static final String SHADOWSOCKS = Type.getInternalName(ShadowSocks.class);
    private static String FIELD_OWNER = "com/github/zhangyinhao1234/test/HelloTrans";

    public static void main(String[] args) throws IOException {

        new ASMTest().reloadClass();
    }

    public byte[] reloadClass() throws IOException {

        FileInputStream inputStream = new FileInputStream(new File("/Users/yinhao.zhang/工作文档/developFolder/GitDataSource/mystudy/study/agent/target/test-classes/com/github/zhangyinhao1234/test/HelloTrans.class"));


        System.out.println("ASMMethod.......");
        ClassReader reader = reader( inputStream);
        // ClassNode将字节码以节点树的形式表示
        ClassNode cn = new ClassNode(ASM7);
        // SKIP_FRAMES用于避免访问帧内容，因为改变字节码的过程中帧内容会被改变，比如局部变量、操作数栈都可能改变。
        reader.accept(cn, ClassReader.SKIP_FRAMES);
        System.out.println("cn.methods.length:" + cn.methods);

        // 进行ShadowSocks属性的添加
        addShadowSocksField(cn.fields);

        for (MethodNode methodNode : cn.methods) {
            System.out.println("methodNode.name：" + methodNode.name);
            if (methodNode.name.equals("<init>")) {
                initShadowSocksField(methodNode);
            }
            if (methodNode.name.equals("hi")) {
                addShadowSocksExecute(methodNode);
            }
        }
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cn.accept(cw);
        // 生成的字节码写入目标文件中
        byte[] outputByteCode = cw.toByteArray();
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("/Users/yinhao.zhang/soft/"+ UUID.randomUUID() +".class");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fos.write(outputByteCode);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return outputByteCode;
    }


    private ClassReader reader(FileInputStream classfileBuffer) throws IOException {
        ClassReader cr = new ClassReader(classfileBuffer);
        return cr;
    }

    /**
     * 给src中的字节码添加ShadowSocks的属性 * @param fields
     */
    private static void addShadowSocksField(List<FieldNode> fields) {
        boolean isHaveShadowSocksFiled = false;
        for (FieldNode fieldNode : fields) {
            if (fieldNode.desc.equals(SHADOWSOCKS_DESCRIPTER)) {
                isHaveShadowSocksFiled = true;
                break;
            }
        }

        if (!isHaveShadowSocksFiled) {
            fields.add(new FieldNode(ACC_PRIVATE, "shadowsocks", SHADOWSOCKS_DESCRIPTER, null, null));
        }
    }


    /**
     * 在构造方法中对ShadowSocks属性进行初始化 * @param methodNode 表示该字节码一个方法节点的值
     */
    private static void initShadowSocksField(MethodNode methodNode) {
        System.out.println("initShadowSocksField.........");
        AbstractInsnNode[] nodes = methodNode.instructions.toArray();
        int length = nodes.length;
        // 初始化相关的字节码指令
        InsnList insnList = new InsnList();
        insnList.add(new VarInsnNode(ALOAD, 0));
        insnList.add(new TypeInsnNode(Opcodes.NEW, SHADOWSOCKS));
        insnList.add(new InsnNode(DUP));
        insnList.add(new MethodInsnNode(INVOKESPECIAL, SHADOWSOCKS, "<init>", "()V", false));
        insnList.add(new FieldInsnNode(PUTFIELD, FIELD_OWNER, "shadowsocks", SHADOWSOCKS_DESCRIPTER));

        methodNode.instructions.insertBefore(nodes[length - 1], insnList);
        System.out.println("initShadowSocksField.........end");
    }

    /**
     * 在searchByGoogle方法调用中进行ShadowSocks的startProxy和stopProxy调用。 * @param methodNode 表示该字节码一个方法节点的值
     */
    private static void addShadowSocksExecute(MethodNode methodNode) {
        System.out.println("addShadowSocksExecute.........");
        AbstractInsnNode[] nodes = methodNode.instructions.toArray();
        int length = nodes.length;
        // searchByGoogle方法前面添加上ShadowSocks的startProxy方法调用
        InsnList startInsnList = new InsnList();
        startInsnList.add(new VarInsnNode(ALOAD, 0));
        startInsnList.add(new FieldInsnNode(GETFIELD, FIELD_OWNER, "shadowsocks", SHADOWSOCKS_DESCRIPTER));
        startInsnList.add(new MethodInsnNode(INVOKEVIRTUAL, SHADOWSOCKS, "startProxy", "()V", false));
        methodNode.instructions.insertBefore(nodes[0], startInsnList);

        // searchByGoogle方法的后面加上ShadowSocks的stopProxy方法的调用
        InsnList endInsnList = new InsnList();
        endInsnList.add(new VarInsnNode(ALOAD, 0));
        endInsnList.add(new FieldInsnNode(GETFIELD, FIELD_OWNER, "shadowsocks", SHADOWSOCKS_DESCRIPTER));
        endInsnList.add(new MethodInsnNode(INVOKEVIRTUAL, SHADOWSOCKS, "stopProxy", "()V", false));
        methodNode.instructions.insertBefore(nodes[length - 1], endInsnList);
    }

}
