package com.farumazula.stickifyapi.service;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.lang.NonNull;

import java.util.Optional;

/**
 * @author Ma1iket
 **/

public interface ThumbnailsService {

    Optional<ByteArrayResource> generatePng(@NonNull ByteArrayResource image);


}
