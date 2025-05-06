package com.farumazula.stickifyapi.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TelegramFeignConfig {

    @Value("${telegram.bot.token}")
    private String token;


    @Bean
    public RequestInterceptor telegramTokenInterceptor() {
        return requestTemplate -> {
            String originalPath = requestTemplate.path();
            String newPath = originalPath.replace("@token@", token);
            requestTemplate.uri(newPath);
        };
    }
}
