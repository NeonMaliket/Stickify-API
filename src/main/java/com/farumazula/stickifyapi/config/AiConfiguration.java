package com.farumazula.stickifyapi.config;

import org.springframework.ai.image.ImageMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class AiConfiguration {

    @Bean("systemStickerGenerationPrompt")
    public ImageMessage systemPrompt() {
        return new ImageMessage("""                
                Create a highly detailed sticker
                of {},
                strictly following the description.
                The sticker must be on a pure white background,
                with crisp, clean edges.
                Do not use any white color in the sticker itself—only the
                background should be white. Ensure the sticker design accurately
                reflects the prompt. Center the sticker, leave no shadows or
                additional elements.
                """);
    }

}
