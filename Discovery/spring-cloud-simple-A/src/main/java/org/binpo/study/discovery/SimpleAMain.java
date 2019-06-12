package org.binpo.study.discovery;/**
 * @author 【张殷豪】
 * Date 2019/6/10 15:56
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author yinhao.zhang
 * @Date 2019/6/10 15:56
 **/
@SpringCloudApplication
@EnableFeignClients(basePackages = "org.binpo")//远程调用扫描的包
@EnableAutoConfiguration
//@ComponentScan()
public class SimpleAMain {
    public static void main(String[] args) {
        SpringApplication.run(SimpleAMain.class, args);
    }


    @Bean
    public MyDiscoveryEnabledStrategy myDiscoveryEnabledStrategy() {
        System.out.println("------- bean MyDiscoveryEnabledStrategy -------");
        return new MyDiscoveryEnabledStrategy();
    }

}
