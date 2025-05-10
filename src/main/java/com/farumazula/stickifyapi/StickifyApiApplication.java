package com.farumazula.stickifyapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class StickifyApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(StickifyApiApplication.class, args);
    }

}
