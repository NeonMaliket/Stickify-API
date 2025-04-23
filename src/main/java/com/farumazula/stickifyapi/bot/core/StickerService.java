package com.farumazula.stickifyapi.bot.core;

import com.farumazula.stickifyapi.bot.core.domain.StickerMeta;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.lang.NonNull;

/**
 * @author Ma1iket
 **/

public interface StickerService {

    void createStickerSet(@NonNull StickerMeta meta, @NonNull ByteArrayResource initialSticker);
}
