package com.github.zhangyinhao1234.study.drools;

import org.kie.api.KieServices;

/**
 * @Author yinhao.zhang 单实例RuleBase生成工具
 * @Date 2020/3/16 2:50 下午
 **/
public class RuleBaseFacatory {

    public static KieServices getRuleBase(){
        KieServices kieServices = KieServices.Factory.get();
        return kieServices;
    }
}
