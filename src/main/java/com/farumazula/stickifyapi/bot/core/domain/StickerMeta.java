package com.farumazula.stickifyapi.bot.core.domain;

import org.telegram.telegrambots.meta.api.objects.InputFile;

/**
 * @author Ma1iket
 **/

public record StickerMeta(String userId,
                          String name,
                          String title,
                          InputFile pngSticker,
                          String emojis) {
}
