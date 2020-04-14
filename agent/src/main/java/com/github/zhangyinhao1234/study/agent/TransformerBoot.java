package com.github.zhangyinhao1234.study.agent;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

public class TransformerBoot {

    public static void premain(String agentArgs, Instrumentation inst)
            throws ClassNotFoundException, UnmodifiableClassException {
        //添加需要监测的类
        inst.addTransformer(new Transformer());
    }

}
