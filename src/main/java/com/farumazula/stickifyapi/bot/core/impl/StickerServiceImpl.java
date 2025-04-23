package com.farumazula.stickifyapi.bot.core.impl;

import com.farumazula.stickifyapi.bot.core.StickerService;
import com.farumazula.stickifyapi.bot.core.domain.StickerMeta;
import com.farumazula.stickifyapi.bot.feign.TelegramBotFeign;
import com.farumazula.stickifyapi.service.ThumbnailsService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.telegrambots.meta.api.methods.stickers.AddStickerToSet;
import org.telegram.telegrambots.meta.api.methods.stickers.CreateNewStickerSet;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.stickers.InputSticker;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.List;

/**
 * @author Ma1iket
 **/


@Slf4j
@Service
@RequiredArgsConstructor
public class StickerServiceImpl implements StickerService {

    @Value("${telegram.bot.name}")
    private String botName;

    private final AbilityBot abilityBot;
    private final ThumbnailsService thumbnailsService;

    @Override
    @SneakyThrows
    public void createStickerSet(@NonNull StickerMeta meta, @NonNull ByteArrayResource initialSticker) {
        var emoji = "\uD83E\uDD23";
        thumbnailsService.generatePng(initialSticker).ifPresent(pngSticker -> {
            try {
                var sticker = InputSticker.builder()
                        .emoji(emoji)
                        .sticker(new InputFile(pngSticker.getInputStream(), "initial_sticker"))
                        .build();
                var createNewStickerSet = new CreateNewStickerSet();
                createNewStickerSet.setUserId(Long.parseLong(meta.userId()));
                createNewStickerSet.setName(meta.name().concat("_by_").concat(botName));
                createNewStickerSet.setTitle(meta.title());
                createNewStickerSet.setStickerFormat("static");
                createNewStickerSet.setStickers(List.of(sticker));
                abilityBot.execute(createNewStickerSet);
            } catch (IOException e) {
                log.warn("'Service' error png sticker read: {}", e.getMessage());
            } catch (TelegramApiException e) {
                log.warn("'Service' sticker creation error: {}", e.getMessage());
            }
        });

    }
}
