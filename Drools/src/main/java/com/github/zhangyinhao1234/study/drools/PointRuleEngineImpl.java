package com.github.zhangyinhao1234.study.drools;

import org.drools.compiler.compiler.DroolsParserException;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;

import java.io.IOException;

/**
 * @Author yinhao.zhang
 * @Date 2020/3/16 2:36 下午
 **/
public class PointRuleEngineImpl implements PointRuleEngine {
    private KieServices ruleBase;
    KieContainer kc;
    @Override
    public void initEngine() {
        ruleBase = RuleBaseFacatory.getRuleBase();
        kc = ruleBase.getKieClasspathContainer();
    }

    @Override
    public void refreshEnginRule() {

    }

    @Override
    public void executeRuleEngine(PointParams pointParams) {

    }
}
