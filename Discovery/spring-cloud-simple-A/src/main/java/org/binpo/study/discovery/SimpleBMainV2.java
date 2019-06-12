package org.binpo.study.discovery;/**
 * @author 【张殷豪】
 * Date 2019/6/10 15:56
 */

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @Author yinhao.zhang
 * @Date 2019/6/10 15:56
 **/
@SpringCloudApplication
@EnableFeignClients(basePackages = "org.binpo")//远程调用扫描的包
//@ComponentScan("org.binpo")
public class SimpleBMainV2 {
    public static void main(String[] args) {
        SpringApplication.run(SimpleBMainV2.class, args);
    }

}
