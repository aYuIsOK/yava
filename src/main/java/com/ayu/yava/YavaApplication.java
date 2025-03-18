package com.ayu.yava;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ayu.yava.mapper")
public class YavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(YavaApplication.class, args);
    }

}
