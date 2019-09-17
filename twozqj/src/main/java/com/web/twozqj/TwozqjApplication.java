package com.web.twozqj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling // 定时器入口
public class TwozqjApplication {

    public static void main(String[] args) {
        SpringApplication.run(TwozqjApplication.class, args);
    }

}
