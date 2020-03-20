package com.sunjy.secret.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.sunjy.secret.boot.mapper")
public class DemoClassApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoClassApplication.class, args);
    }


}
