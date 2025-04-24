package com.farumazula.stickifyapi.service;

import com.farumazula.stickifyapi.dto.GeneratePromptDto;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.lang.NonNull;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

/**
 * @author Ma1iket
 **/


public interface StickifyService {

    Optional<ByteArrayResource> aiGeneration(@NonNull GeneratePromptDto prompt);

    Optional<ByteArrayResource> saveStickers(@NonNull String chatId, @NonNull MultipartFile stickers);

}
