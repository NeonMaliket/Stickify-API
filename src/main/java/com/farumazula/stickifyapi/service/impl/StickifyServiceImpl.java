package com.farumazula.stickifyapi.service.impl;

import com.farumazula.stickifyapi.dto.GeneratePromptDto;
import com.farumazula.stickifyapi.exception.TelegramBotException;
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

    @Override
    public Optional<ByteArrayResource> aiGeneration(@NonNull GeneratePromptDto prompt) {
        log.info("'Service' AI generation Prompt: {}", prompt);
        return aiService.generateStickerByPrompt(prompt);
    }

    @Override
    @SneakyThrows
    public Optional<ByteArrayResource> saveSticker(@NonNull String chatId, @NonNull MultipartFile sticker) {
        log.info("'Service' Save sticker: {}", sticker);
        var resource = new ByteArrayResource(sticker.getBytes());
        var byteArrayResource = thumbnailsService.generatePng(resource);

        byteArrayResource.ifPresent(convertedPng -> {
            try {
                abilityBot.execute(new SendSticker(chatId, new InputFile(convertedPng.getInputStream(), "sticker.png")));
            } catch (TelegramApiException | IOException e) {
                log.warn("'Service' Error: {}", e.getMessage());
                throw new TelegramBotException(e);
            }
        });


        return byteArrayResource;
    }
}
