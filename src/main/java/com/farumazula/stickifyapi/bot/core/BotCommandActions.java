package com.farumazula.stickifyapi.bot.core;

import org.springframework.lang.NonNull;
import org.telegram.abilitybots.api.objects.MessageContext;

public interface BotCommandActions {

    void menuCommand(@NonNull MessageContext ctx);

}
