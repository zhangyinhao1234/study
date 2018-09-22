package com.github.zhangyinhao1234.study.spring_boot_apollo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;


@SpringBootApplication
@EnableApolloConfig
@ComponentScan({"com.github.zhangyinhao1234.study"})
public class ApolloMainExample {

    public static void main(String[] args) {
        SpringApplication.run(ApolloMainExample.class, args);
    }
}
