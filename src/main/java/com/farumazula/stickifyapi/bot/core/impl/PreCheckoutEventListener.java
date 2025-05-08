package com.farumazula.stickifyapi.bot.core.impl;

import com.farumazula.stickifyapi.bot.core.BotEventListener;
import com.farumazula.stickifyapi.exception.TelegramBotException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.telegrambots.meta.api.methods.AnswerPreCheckoutQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * @author Ma1iket
 **/

@Slf4j
@Component
public class PreCheckoutEventListener implements BotEventListener {
    private static final String CHECKOUT_ERROR = "Checkout Error";
    private final AbilityBot abilityBot;

    public PreCheckoutEventListener(@Lazy AbilityBot abilityBot) {
        this.abilityBot = abilityBot;
    }

    @Override
    public void onEvent(@NonNull Update update) {
        log.info("'Bot' PreCheckout event for user: {}", update.getMessage().getFrom().getUserName());
        if (update.hasPreCheckoutQuery()) {
            var preCheckoutQuery = update.getPreCheckoutQuery();
            var answerPreCheckoutQuery = new AnswerPreCheckoutQuery(
                    preCheckoutQuery.getId(),
                    true
            );
            try {
                abilityBot.execute(answerPreCheckoutQuery);
            } catch (TelegramApiException e) {
                var errorAnswer = new AnswerPreCheckoutQuery(
                        preCheckoutQuery.getId(),
                        false,
                        CHECKOUT_ERROR
                );
                try {
                    abilityBot.execute(errorAnswer);
                } catch (TelegramApiException ex) {
                    throw new TelegramBotException(CHECKOUT_ERROR);
                }
            }
        }
    }
}
