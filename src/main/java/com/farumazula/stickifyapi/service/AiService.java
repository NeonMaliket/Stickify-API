package com.farumazula.stickifyapi.service;

import com.farumazula.stickifyapi.dto.GeneratePromptDto;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.lang.NonNull;

import java.util.Optional;

/**
 * @author Ma1iket
 **/

public interface AiService {

    Optional<ByteArrayResource> generateStickerByPrompt(@NonNull GeneratePromptDto prompt);

}
