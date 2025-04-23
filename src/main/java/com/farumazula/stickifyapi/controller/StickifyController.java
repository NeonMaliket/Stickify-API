package com.farumazula.stickifyapi.controller;

import com.farumazula.stickifyapi.bot.core.domain.StickerMeta;
import com.farumazula.stickifyapi.dto.GeneratePromptDto;
import com.farumazula.stickifyapi.service.StickifyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    @PostMapping("/upload/{chatId}")
    public ResponseEntity<ByteArrayResource> uploadSticker(@PathVariable String chatId, @RequestParam MultipartFile sticker) {
        log.info("'Controller' Is sticker is empty: {}", sticker.isEmpty());
        var byteArrayResource = stickifyService.saveStickers(chatId, sticker);

        return byteArrayResource
                .map(png -> ResponseEntity.ok()
                        .header("Content-Type", "image/jpeg")
                        .header("Content-Length", String.valueOf(png.contentLength()))
                        .header("Content-Disposition", "attachment; filename=\"sticker.jpg\"")
                        .body(png))
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PostMapping("/sticker-pack")
    public ResponseEntity<StickerMeta> createStickerPack(
            @RequestParam MultipartFile initialSticker,
            @RequestParam String userId,
            @RequestParam String name,
            @RequestParam String title,
            @RequestParam String emojis
    ) {
        var stickerMeta = new StickerMeta(userId, name, title, null, emojis);
        log.info("'Controller' Creating sticker pack: {}", stickerMeta);
        return ResponseEntity.of(stickifyService.createNewStickerPack(stickerMeta, initialSticker));
    }

    @GetMapping("/sticker")
    public ResponseEntity<Object> getSticker(@RequestParam String fileId) {
        log.info("'Controller' Searching sticker: {}", fileId);
        return ResponseEntity.of(stickifyService.shareSticker(fileId));
    }

    @GetMapping("/stickers/{chatId}")
    public ResponseEntity<List<Object>> getAllStickersByChatId(@PathVariable String chatId) {
        log.info("'Controller' Getting all stickers for chat: {}", chatId);
        return ResponseEntity.ok(stickifyService.getAllStickersByChatId(chatId));
    }
}
