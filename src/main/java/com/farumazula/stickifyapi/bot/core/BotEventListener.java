package com.farumazula.stickifyapi.bot.core;

import org.springframework.lang.NonNull;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author Ma1iket
 **/

public interface BotEventListener {

    void onEvent(@NonNull Update update);

}
