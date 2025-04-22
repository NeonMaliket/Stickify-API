package com.farumazula.stickifyapi.service.impl;

import com.farumazula.stickifyapi.dto.GeneratePromptDto;
import com.farumazula.stickifyapi.service.AiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

/**
 * @author Ma1iket
 **/

@Slf4j
@Service
@RequiredArgsConstructor
public class AiServiceImpl implements AiService {

    @Override
    public Optional<ByteArrayResource> generateStickerByPrompt(@NonNull GeneratePromptDto prompt) {
        log.info("'Service' Generating sticker: {}", prompt);

        try {
            var file = Files.readAllBytes(Paths.get("21.jpg"));
            var response = new ByteArrayResource(file);

            log.info("'Service' Capacity: {}", response.contentLength());
            return Optional.of(response);
        } catch (IOException e) {
            log.warn("'Service' Error: {}", e.getMessage());
            return Optional.empty();
        }
    }
}
