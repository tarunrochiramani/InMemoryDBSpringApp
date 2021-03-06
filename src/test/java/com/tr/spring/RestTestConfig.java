package com.tr.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(RestConfig.class)
//@EntityScan(basePackages = "com.tr.entity")
public class RestTestConfig {
    public static void main(String[] args) {
        SpringApplication.run(RestTestConfig.class, args);
    }
}
