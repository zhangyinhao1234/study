package org.binpo.study.discovery;/**
 * @author 【张殷豪】
 * Date 2019/6/10 15:56
 */

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author yinhao.zhang
 * @Date 2019/6/10 15:56
 **/
@RestController
public class SimpleAController {

    @Autowired
    SimpleAHelloFeign helloFeign;

    @Value("${eureka.instance.metadataMap.version}")
    String version;

    @Value("${spring.application.name}")
    String applicationName;


    @RequestMapping("/simplea/hello")
    public Map hello() {

//        Config appConfig = ConfigService.getAppConfig();
//        for (String pname : appConfig.getPropertyNames()) {
//            System.out.println("properties : " + pname + " : " + appConfig.getProperty(pname, null));
//        }
        System.out.println("appName: " + System.getProperty("appName") + ":  version: " + version + "-----SimpleAController .... hello ....");
        Map obj = new HashMap();
        obj.put("success", version);
        if (applicationName.contains("a")) {
            Map map = helloFeign.sayHello();
            obj.put("success", map.get("success"));
        }
        return obj;
    }


}
