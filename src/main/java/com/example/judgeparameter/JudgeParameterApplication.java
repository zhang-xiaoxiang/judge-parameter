package com.example.judgeparameter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JudgeParameterApplication {

    public static void main(String[] args) {
        SpringApplication.run(JudgeParameterApplication.class, args);
        System.out.println("请使用postman模拟json格式访问接口");
        System.out.println("http://localhost:8080/add-user");
    }

}
