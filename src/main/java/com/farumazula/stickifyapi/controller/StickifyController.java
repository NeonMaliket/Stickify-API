package com.farumazula.stickifyapi.controller;

import com.farumazula.stickifyapi.dto.GeneratePromptDto;
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

    //TODO: connect with OpenAI API image generation
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
                .orElseGet(() -> ResponseEntity.noContent().build());

    }

    @PostMapping("/upload")
    public ResponseEntity<Object> uploadSticker(@RequestParam MultipartFile sticker) {
        log.info("'Controller' Is sticker is empty: {}", sticker.isEmpty());
        return ResponseEntity.of(stickifyService.saveStickers(sticker));
    }

    @GetMapping("/sticker")
    public ResponseEntity<Object> getSticker(@RequestParam String fileId) {
        log.info("'Controller' Searching sticker: {}", fileId);
        return ResponseEntity.of(stickifyService.shareSticker(fileId));
    }
}
