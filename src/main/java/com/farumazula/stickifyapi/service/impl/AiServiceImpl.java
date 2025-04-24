package com.farumazula.stickifyapi.service.impl;

import com.farumazula.stickifyapi.dto.GeneratePromptDto;
import com.farumazula.stickifyapi.service.AiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.image.ImageMessage;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Optional;

/**
 * @author Ma1iket
 **/

@Slf4j
@Service
@RequiredArgsConstructor
public class AiServiceImpl implements AiService {

    private final OpenAiImageModel imageModel;
    private final ImageMessage systemStickerGenerationPrompt;

    @Override
    public Optional<ByteArrayResource> generateStickerByPrompt(@NonNull GeneratePromptDto prompt) {
        log.info("'Service' Generating sticker: {}", prompt);

        var imageOptions = new OpenAiImageOptions();
        imageOptions.setResponseFormat("b64_json");
        imageOptions.setN(1);
        imageOptions.setSize("1024x1024");
        var imagePrompt = new ImagePrompt(
                systemStickerGenerationPrompt.getText().replace("{}", prompt.prompt()),
                imageOptions
        );

        var response = imageModel.call(imagePrompt);
        if (!response.getResults().isEmpty()) {
            var base64 = response.getResult().getOutput().getB64Json();
            var imageBytes = Base64.getDecoder().decode(base64);
            return Optional.of(new ByteArrayResource(imageBytes));
        }
        return Optional.empty();

    }
}
