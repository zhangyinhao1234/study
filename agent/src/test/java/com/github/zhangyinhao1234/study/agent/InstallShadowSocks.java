package com.github.zhangyinhao1234.study.agent;

import com.github.zhangyinhao1234.asm.*;
import org.objectweb.asm.*;
import org.objectweb.asm.tree.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static org.objectweb.asm.Opcodes.*;


public class InstallShadowSocks {
    private static final String SHADOWSOCKS_DESCRIPTER = Type.getDescriptor(Interceptor.class);
    private static final String SHADOWSOCKS = Type.getInternalName(Interceptor.class);
    private static final String INTERCEPTOR = Type.getInternalName(Interceptor.class);
    private static String FIELD_OWNER = "com/github/zhangyinhao1234/study/agent/LinuxService";

    public static void main(String[] args) {
//        FIELD_OWNER = args[1].substring(0, args[1].length() - 6);
        // 其中arg[0]是源字节码文件，args[1]是目标字节码文件
        String src="/Users/yinhao.zhang/工作文档/developFolder/GitDataSource/mystudy/study/agent/target/test-classes/com/github/zhangyinhao1234/study/agent/LinuxService.class";
        String dst="/Users/yinhao.zhang/soft/"+ UUID.randomUUID() +".class";
        installShadowSocks(src, dst);
    }

    /**
     * 进行ShadowSocks的安装 * @param src 源字节码 * @param dst 目标字节码
     */
    public static void installShadowSocks(String src, String dst) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            byte[] outputByteCode;
            fis = new FileInputStream(src);
            // ClassReader读入字节码
            ClassReader cr = new ClassReader(fis);
            // ClassNode将字节码以节点树的形式表示
            ClassNode cn = new ClassNode(ASM7);
            // SKIP_FRAMES用于避免访问帧内容，因为改变字节码的过程中帧内容会被改变，比如局部变量、操作数栈都可能改变。
            cr.accept(cn, ClassReader.SKIP_FRAMES);

            // 进行ShadowSocks属性的添加
            addShadowSocksField(cn.fields);

            for (MethodNode methodNode : cn.methods) {
                if (methodNode.name.equals("<init>")) {
                    // 构造器中对ShadowSocks属性进行初始化
                    initShadowSocksField(methodNode);
                } else if (methodNode.name.equals("searchByGoogle")) {
                    // searchByGoogle方法中添加ShadowSocks的调用
                    addShadowSocksExecute(methodNode);
                }
            }
            // COMPUTE_FRAMES表示ASM会自动计算所有内容，visitFrame和visitMaxs方法都会被忽略掉
            // 还有一个COMPUTE_MAXS是会自定计算局部变量表和操作数栈的大小，visitMaxs会被忽略掉。
            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
            cn.accept(cw);
            // 生成的字节码写入目标文件中
            outputByteCode = cw.toByteArray();
            fos = new FileOutputStream(dst);
            fos.write(outputByteCode);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 给src中的字节码添加ShadowSocks的属性 * @param fields
     */
    private static void addShadowSocksField(List<FieldNode> fields) {
//        boolean isHaveShadowSocksFiled = false;
//        for (FieldNode fieldNode : fields) {
//            if (fieldNode.desc.equals(SHADOWSOCKS_DESCRIPTER)) {
//                isHaveShadowSocksFiled = true;
//                break;
//            }
//        }
//
//        if (!isHaveShadowSocksFiled) {
//            fields.add(new FieldNode(ACC_PRIVATE, "shadowsocks", SHADOWSOCKS_DESCRIPTER, null, null));
//        }
    }

    /**
     * 在构造方法中对ShadowSocks属性进行初始化 * @param methodNode 表示该字节码一个方法节点的值
     */
    private static void initShadowSocksField(MethodNode methodNode) {
//        AbstractInsnNode[] nodes = methodNode.instructions.toArray();
//        int length = nodes.length;
//        // 初始化相关的字节码指令
//        InsnList insnList = new InsnList();
//        insnList.add(new VarInsnNode(ALOAD, 0));
//        insnList.add(new TypeInsnNode(NEW, SHADOWSOCKS));
//        insnList.add(new InsnNode(DUP));
//        insnList.add(new MethodInsnNode(INVOKESPECIAL, SHADOWSOCKS, "<init>", "()V", false));
//        insnList.add(new FieldInsnNode(PUTFIELD, FIELD_OWNER, "aInterceptor", SHADOWSOCKS_DESCRIPTER));
//
//        methodNode.instructions.insertBefore(nodes[length - 1], insnList);
    }

    /**
     * 在searchByGoogle方法调用中进行ShadowSocks的startProxy和stopProxy调用。 * @param methodNode 表示该字节码一个方法节点的值
     */
    private static void addShadowSocksExecute(MethodNode methodNode) {
//        AbstractInsnNode[] nodes = methodNode.instructions.toArray();
//        int length = nodes.length;
//        // searchByGoogle方法前面添加上ShadowSocks的startProxy方法调用
//        InsnList startInsnList = new InsnList();
//        startInsnList.add(new VarInsnNode(ALOAD, 0));
//        startInsnList.add(new FieldInsnNode(GETFIELD, FIELD_OWNER, "aInterceptor", SHADOWSOCKS_DESCRIPTER));
//        startInsnList.add(new MethodInsnNode(INVOKESTATIC, SHADOWSOCKS, "getInterceptor", "(I)" + Type.getDescriptor(Interceptor.class), false));
//        methodNode.instructions.insertBefore(nodes[0], startInsnList);

//        // searchByGoogle方法的后面加上ShadowSocks的stopProxy方法的调用
//        InsnList endInsnList = new InsnList();
//        endInsnList.add(new VarInsnNode(ALOAD, 0));
//        endInsnList.add(new FieldInsnNode(GETFIELD, FIELD_OWNER, "shadowsocks", SHADOWSOCKS_DESCRIPTER));
//        endInsnList.add(new MethodInsnNode(INVOKEVIRTUAL, SHADOWSOCKS, "stopProxy", "()V", false));
//        methodNode.instructions.insertBefore(nodes[5], endInsnList);

        AbstractInsnNode[] nodes = methodNode.instructions.toArray();

        InsnList startInsnList = new InsnList();
//        startInsnList.add(new VarInsnNode(ALOAD, 0));

        String name="aInterceptor";
        String desc="Lcom/github/zhangyinhao1234/asm/Interceptor;";


        final LocalVariableNode node = new LocalVariableNode(name, desc, null, new LabelNode(), new LabelNode(), 2);
        methodNode.localVariables.add(node);
        push(startInsnList,1);

        startInsnList.add(new MethodInsnNode(Opcodes.INVOKESTATIC, Type.getInternalName(InterceptorFactory.class), "getInterceptor", "(I)" + Type.getDescriptor(Interceptor.class), false));
        storeVar(startInsnList, 2);

        startInsnList.add(new VarInsnNode(ALOAD, 2));

        startInsnList.add(new MethodInsnNode(INVOKEINTERFACE, INTERCEPTOR, "before", "()V", false));
        methodNode.instructions.insertBefore(nodes[0], startInsnList);



    }


    private static void addRunTime(MethodNode methodNode) {
        AbstractInsnNode[] nodes = methodNode.instructions.toArray();
        InsnList startInsnList = new InsnList();



    }

    static void push(InsnList insnList, final int value) {
        if (value >= -1 && value <= 5) {
            insnList.add(new InsnNode(Opcodes.ICONST_0 + value));
        } else if (value >= Byte.MIN_VALUE && value <= Byte.MAX_VALUE) {
            insnList.add(new IntInsnNode(Opcodes.BIPUSH, value));
        } else if (value >= Short.MIN_VALUE && value <= Short.MAX_VALUE) {
            insnList.add(new IntInsnNode(Opcodes.SIPUSH, value));
        } else {
            insnList.add(new LdcInsnNode(value));
        }
    }

    static void storeVar(final InsnList instructions, final int index) {
        instructions.add(new VarInsnNode(Opcodes.ASTORE, index));
    }

    static void loadNull(final InsnList instructions) {
        instructions.add(new InsnNode(Opcodes.ACONST_NULL));
    }

}
