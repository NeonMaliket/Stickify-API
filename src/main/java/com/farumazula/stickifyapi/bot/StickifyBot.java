package com.farumazula.stickifyapi.bot;

import com.farumazula.stickifyapi.bot.core.BotCommandActions;
import lombok.extern.slf4j.Slf4j;
import org.mapdb.DBMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.db.MapDBContext;
import org.telegram.abilitybots.api.objects.Ability;

import static org.telegram.abilitybots.api.objects.Locality.ALL;
import static org.telegram.abilitybots.api.objects.Privacy.PUBLIC;

/**
 * @author Ma1iket
 **/

@Slf4j
@Service
public class StickifyBot extends AbilityBot {
    private final long creatorId;

    private final BotCommandActions botCommandActions;

    @Autowired
    public StickifyBot(
            @Value("${telegram.bot.token}") String token,
            @Value("${telegram.bot.name}") String botName,
            @Value("${telegram.bot.creator.id}") long creatorId,
            @Lazy BotCommandActions botCommandActions
    ) {
        super(token, botName, new MapDBContext(DBMaker
                .memoryDB()
                .make()));
        this.creatorId = creatorId;
        this.botCommandActions = botCommandActions;
    }

    public Ability startBot() {
        return Ability
                .builder()
                .name("start")
                .info("info")
                .locality(ALL)
                .privacy(PUBLIC)
                .action(botCommandActions::startCommand)
                .build();
    }

    @Override
    public long creatorId() {
        return creatorId;
    }
}
