package com.farumazula.stickifyapi.bot.core.impl;

import com.farumazula.stickifyapi.bot.core.BotCommandActions;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.telegram.abilitybots.api.objects.MessageContext;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

/**
 * @author Ma1iket
 **/

@Slf4j
@Service
@RequiredArgsConstructor
public class BotCommandActionsImpl implements BotCommandActions {

    @Override
    @SneakyThrows
    public void startCommand(@NonNull MessageContext ctx) {
        log.info("'Bot' Start command");
        var method = new SendMessage(ctx.chatId().toString(), "Hello, I'm Stickify bot!");
        ctx.bot().execute(method);
    }
}
