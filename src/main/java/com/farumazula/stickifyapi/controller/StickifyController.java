package com.farumazula.stickifyapi.controller;

import com.farumazula.stickifyapi.dto.GeneratePromptDto;
import com.farumazula.stickifyapi.exception.AiException;
import com.farumazula.stickifyapi.exception.TelegramBotException;
import com.farumazula.stickifyapi.service.StickifyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Slf4j
@RestController
@RequestMapping("/api/v1/stickify")
@RequiredArgsConstructor
class StickifyController {

    private final StickifyService stickifyService;

    @PostMapping("/generate")
    public ResponseEntity<ByteArrayResource> generateSticker(
            @Valid @RequestBody GeneratePromptDto prompt
    ) {
        log.info("'Controller' Prompt: {}", prompt);
        var byteArrayResource = stickifyService.aiGeneration(prompt);

        return byteArrayResource
                .map(sticker -> ResponseEntity.ok()
                        .header("Content-Type", "image/jpeg")
                        .header("Content-Length", String.valueOf(sticker.contentLength()))
                        .header("Content-Disposition", "attachment; filename=\"sticker.jpg\"")
                        .body(sticker))
                .orElseThrow(() -> new AiException("AI generation failed"));

    }

    @PostMapping("/upload/{chatId}")
    public ResponseEntity<Void> uploadSticker(@PathVariable String chatId, @RequestParam MultipartFile sticker) {
        log.info("'Controller' Is sticker is empty: {}", sticker.isEmpty());
        stickifyService.saveSticker(chatId, sticker)
                .orElseThrow(() -> new TelegramBotException("Upload sticker failed"));
        return ResponseEntity.ok().build();
    }
}
