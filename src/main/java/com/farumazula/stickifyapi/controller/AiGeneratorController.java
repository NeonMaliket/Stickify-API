package com.farumazula.stickifyapi.controller;

import com.farumazula.stickifyapi.dto.GeneratePromptDto;
import com.farumazula.stickifyapi.service.AiService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AiGeneratorController {

    private final AiService aiService;

    @PostMapping("/generate-image")
    public ResponseEntity<ByteArrayResource> generateImage(@RequestBody GeneratePromptDto prompt) {
        var result = aiService.generateStickerByPrompt(prompt);

        return result
                .map(sticker -> ResponseEntity.ok()
                        .header("Content-Type", "image/jpeg")
                        .header("Content-Length", String.valueOf(sticker.contentLength()))
                        .header("Content-Disposition", "attachment; filename=\"generated.jpg\"")
                        .body(sticker))
                .orElseGet(() -> ResponseEntity.noContent().build());
    }
}
