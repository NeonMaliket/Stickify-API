package com.farumazula.stickifyapi.service;

import com.farumazula.stickifyapi.bot.core.domain.StickerMeta;
import com.farumazula.stickifyapi.dto.GeneratePromptDto;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.lang.NonNull;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

/**
 * @author Ma1iket
 **/


public interface StickifyService {

    Optional<ByteArrayResource> aiGeneration(@NonNull GeneratePromptDto prompt);

    Optional<ByteArrayResource> saveStickers(@NonNull String chatId, @NonNull MultipartFile stickers);

    Optional<Object> shareSticker(@NonNull String id);

    List<Object> getAllStickersByChatId(@NonNull String chatId);

    Optional<StickerMeta> createNewStickerPack(@NonNull StickerMeta stickerMeta, MultipartFile initialSticker);
}
