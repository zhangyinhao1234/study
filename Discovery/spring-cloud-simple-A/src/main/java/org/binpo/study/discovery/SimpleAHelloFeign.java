package org.binpo.study.discovery;/**
 * @author 【张殷豪】
 * Date 2019/6/10 15:57
 */

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * @Author yinhao.zhang
 *
 * 当需要从Spring cloud 改造到Service mesh 需要加上  url指定服务名，由sidecar进行路由
 * @Date 2019/6/10 15:57
 **/
@FeignClient(name = "simple-b", configuration = SimpleAConfig.class)
public interface SimpleAHelloFeign {
    @RequestMapping(value = "/simplea/hello", method = RequestMethod.GET)
    Map sayHello();
}
