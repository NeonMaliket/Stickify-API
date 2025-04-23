package com.farumazula.stickifyapi.config;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.sender.MessageSender;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

/**
 * @author Ma1iket
 **/

@Configuration
public class StickifyBotConfig {

    @Bean
    @SneakyThrows(TelegramApiException.class)
    public TelegramBotsApi botsApi(AbilityBot abilityBot) {
        var telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(abilityBot);
        return telegramBotsApi;
    }

    @Bean
    public MessageSender messageSender(AbilityBot abilityBot) {
        return abilityBot.sender();
    }
}
