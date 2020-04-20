package com.github.zhangyinhao1234.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import static org.objectweb.asm.Opcodes.*;

public class ASMMethod {

    private static final String SHADOWSOCKS_DESCRIPTER = Type.getDescriptor(ShadowSocks.class);
    private static final String SHADOWSOCKS = Type.getInternalName(ShadowSocks.class);
    private static final String INTERCEPTOR = Type.getInternalName(Interceptor.class);
    private static String FIELD_OWNER = "1";


    public byte[] reloadClass(byte[] classfileBuffer) {
        System.out.println("ASMMethod.......");
        ClassReader reader = reader(classfileBuffer);
        // ClassNode将字节码以节点树的形式表示
        ClassNode cn = new ClassNode(ASM7);
        // SKIP_FRAMES用于避免访问帧内容，因为改变字节码的过程中帧内容会被改变，比如局部变量、操作数栈都可能改变。
        reader.accept(cn, ClassReader.SKIP_FRAMES);
        System.out.println("cn.methods.length:" + cn.methods);

        // 进行ShadowSocks属性的添加
//        addShadowSocksField(cn.fields);

        for (MethodNode methodNode : cn.methods) {
            System.out.println("methodNode.name：" + methodNode.name);
            if (methodNode.name.equals("<init>")) {
//                initShadowSocksField(methodNode);
            }
            if (methodNode.name.equals("hi")) {
                addShadowSocksExecute(methodNode);
            }
        }
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cn.accept(cw);

        // 生成的字节码写入目标文件中
        byte[] outputByteCode = cw.toByteArray();
        Util.saveclass(outputByteCode);
        return outputByteCode;
    }



    private ClassReader reader(byte[] classfileBuffer) {
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
        AbstractInsnNode[] nodes = methodNode.instructions.toArray();

        InsnList startInsnList = new InsnList();
//        startInsnList.add(new VarInsnNode(ALOAD, 0));

        String name="aInterceptor";
        String desc="Lcom/github/zhangyinhao1234/asm/Interceptor;";


        push(startInsnList,1);

//        final LocalVariableNode node = new LocalVariableNode(name, desc, null, new LabelNode(), new LabelNode(), 2);
//        methodNode.localVariables.add(node);

        startInsnList.add(new MethodInsnNode(Opcodes.INVOKESTATIC, Type.getInternalName(InterceptorFactory.class), "getInterceptor", "(I)" + Type.getDescriptor(Interceptor.class), false));

        storeVar(startInsnList, 3);

        startInsnList.add(new VarInsnNode(ALOAD, 3));
        startInsnList.add(new MethodInsnNode(INVOKEINTERFACE, INTERCEPTOR, "before", "()V", false));
        methodNode.instructions.insertBefore(nodes[0], startInsnList);



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


}
