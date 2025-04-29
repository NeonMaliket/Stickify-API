package com.farumazula.stickifyapi.service.impl;

import com.farumazula.stickifyapi.service.ThumbnailsService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.filters.ImageFilter;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.Optional;

/**
 * @author Ma1iket
 **/

@Slf4j
@Service
@RequiredArgsConstructor
public class ThumbnailsServiceImpl implements ThumbnailsService {

    private static final String FORMAT = "PNG";
    private final ImageFilter transparent;

    @Override
    @SneakyThrows
    public Optional<ByteArrayResource> generatePng(@NonNull ByteArrayResource image) {
        var byteArrayOutputStream = new ByteArrayOutputStream();

        Thumbnails.of(image.getInputStream())
                .size(512, 512)
//                .addFilter(transparent)
                .outputFormat(FORMAT)
                .toOutputStream(byteArrayOutputStream);

        var value = new ByteArrayResource(byteArrayOutputStream.toByteArray());

        log.info("'Service' Thumbnail content length: {}", value.contentLength());

        return Optional.of(value);
    }
}
