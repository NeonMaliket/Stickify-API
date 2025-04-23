package com.farumazula.stickifyapi.bot.core.impl;

import com.farumazula.stickifyapi.bot.core.BotCommandActions;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.telegram.abilitybots.api.objects.MessageContext;

/**
 * @author Ma1iket
 **/

@Slf4j
@Service
@RequiredArgsConstructor
public class BotCommandActionsImpl implements BotCommandActions {

    @Override
    @SneakyThrows
    public void menuCommand(@NonNull MessageContext ctx) {
        log.info("'Bot' Menu command");
    }
}
