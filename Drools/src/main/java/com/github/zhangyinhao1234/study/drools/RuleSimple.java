package com.github.zhangyinhao1234.study.drools;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * @Author yinhao.zhang
 * @Date 2020/3/16 3:16 下午
 **/
public class RuleSimple {

    public static void main(String[] args) {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kc = kieServices.getKieClasspathContainer();
        KieSession kieSession = kc.newKieSession("point-rulesKS");
        PointParams pointParams = new PointParams();
        pointParams.setCheckCount(5);
        kieSession.insert(pointParams);
        kieSession.fireAllRules();
        System.out.printf(pointParams.getPoint()+"");
        kieSession.dispose();




    }
}
