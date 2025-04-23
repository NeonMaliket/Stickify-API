package com.farumazula.stickifyapi.service.impl;

import com.farumazula.stickifyapi.bot.core.StickerService;
import com.farumazula.stickifyapi.bot.core.domain.StickerMeta;
import com.farumazula.stickifyapi.dto.GeneratePromptDto;
import com.farumazula.stickifyapi.service.AiService;
import com.farumazula.stickifyapi.service.StickifyService;
import com.farumazula.stickifyapi.service.ThumbnailsService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.telegrambots.meta.api.methods.send.SendSticker;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * @author Ma1iket
 **/

@Slf4j
@Service
@RequiredArgsConstructor
public class StickifyServiceImpl implements StickifyService {

    private final AiService aiService;

    private final AbilityBot abilityBot;

    private final ThumbnailsService thumbnailsService;

    private final StickerService stickerService;

    @Override
    public Optional<ByteArrayResource> aiGeneration(@NonNull GeneratePromptDto prompt) {
        log.info("'Service' AI generation Prompt: {}", prompt);
        return aiService.generateStickerByPrompt(prompt);
    }

    @Override
    @SneakyThrows
    public Optional<ByteArrayResource> saveStickers(@NonNull String chatId, @NonNull MultipartFile stickers) {
        log.info("'Service' Save sticker: {}", stickers);
        var resource = new ByteArrayResource(stickers.getBytes());
        var byteArrayResource = thumbnailsService.generatePng(resource);

        byteArrayResource.ifPresent(convertedPng -> {
            try {
                abilityBot.execute(new SendSticker(chatId, new InputFile(convertedPng.getInputStream(), "sticker.png")));
            } catch (TelegramApiException | IOException e) {
                log.warn("'Service' Error: {}", e.getMessage());
            }
        });


        return byteArrayResource;
    }

    @Override
    public Optional<Object> shareSticker(@NonNull String id) {
        log.info("'Service' Share sticker: {}", id);
        return Optional.empty();
    }

    @Override
    public List<Object> getAllStickersByChatId(@NonNull String chatId) {
        return List.of();
    }

    @Override
    @SneakyThrows
    public Optional<StickerMeta> createNewStickerPack(@NonNull StickerMeta stickerMeta, @NonNull MultipartFile initialSticker) {
        log.info("'Service' Creating new sticker pack: {}", stickerMeta);
        stickerService.createStickerSet(stickerMeta, new ByteArrayResource(initialSticker.getBytes()));
        log.info("'Service' Sticker created");
        return Optional.of(stickerMeta);
    }
}
