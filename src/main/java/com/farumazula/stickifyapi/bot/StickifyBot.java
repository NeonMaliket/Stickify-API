package com.farumazula.stickifyapi.bot;

import com.farumazula.stickifyapi.bot.core.BotCommandActions;
import com.farumazula.stickifyapi.bot.feign.TelegramBotFeign;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.mapdb.DBMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.db.MapDBContext;
import org.telegram.abilitybots.api.objects.Ability;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.send.SendSticker;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;

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
    private final TelegramBotFeign telegramBotFeign;

    private final String token;


    @Autowired
    public StickifyBot(
            @Value("${telegram.bot.token}") String token,
            @Value("${telegram.bot.name}") String botName,
            @Value("${telegram.bot.creator.id}") long creatorId,
            @Lazy BotCommandActions botCommandActions,
            @Lazy TelegramBotFeign telegramBotFeign
    ) {
        super(token, botName, new MapDBContext(DBMaker
                .memoryDB()
                .make()));
        this.creatorId = creatorId;
        this.botCommandActions = botCommandActions;
        this.telegramBotFeign = telegramBotFeign;
        this.token = token;
    }

    @Override
    @SneakyThrows
    public void onUpdateReceived(Update update) {

        log.info("Chat id: {}", update.getMessage().getChatId());

        var fileId = update.getMessage().getSticker().getFileId();

        log.info("Sticker: {}", update.getMessage().getSticker());

        var file = execute(new GetFile(fileId));


        try (var response = telegramBotFeign.downloadFile(token, file.getFilePath())) {
            var resource = response.body().asInputStream().readAllBytes();
            var byteArrayResource = new ByteArrayResource(resource);

            execute(SendSticker.builder()
                    .chatId(update.getMessage().getChatId().toString())
                    .sticker(new InputFile(byteArrayResource.getInputStream(), "sticker.png"))
                    .build());

        }

        super.onUpdateReceived(update);
    }

    public Ability startBot() {
        return Ability
                .builder()
                .name("start")
                .info("info")
                .locality(ALL)
                .privacy(PUBLIC)
                .action(botCommandActions::menuCommand)
                .build();
    }

    @Override
    public long creatorId() {
        return creatorId;
    }
}
