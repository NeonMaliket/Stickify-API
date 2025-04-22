package com.farumazula.stickifyapi.service.impl;

import com.farumazula.stickifyapi.dto.GeneratePromptDto;
import com.farumazula.stickifyapi.service.AiService;
import com.farumazula.stickifyapi.service.StickifyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    @Override
    public Optional<ByteArrayResource> aiGeneration(@NonNull GeneratePromptDto prompt) {
        log.info("'Service' Prompt: {}", prompt);
        return aiService.generateStickerByPrompt(prompt);
    }

    @Override
    public Optional<Object>  saveStickers(@NonNull MultipartFile stickers) {
        return Optional.empty();
    }

    @Override
    public Optional<Object> shareSticker(@NonNull String id) {
        return Optional.empty();
    }

    @Override
    public List<Object> getAllStickersByChatId(String chatId) {
        return List.of();
    }
}
