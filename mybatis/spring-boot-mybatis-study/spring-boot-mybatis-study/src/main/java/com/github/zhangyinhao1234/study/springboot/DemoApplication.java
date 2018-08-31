package com.github.zhangyinhao1234.study.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = {"com.github.zhangyinhao1234.study.springboot.dao"})
@ComponentScan({"com.github.zhangyinhao1234.study.springboot","com.github.zhangyinhao1234.study"})
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
