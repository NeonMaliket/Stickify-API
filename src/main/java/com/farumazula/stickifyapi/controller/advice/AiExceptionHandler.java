package com.farumazula.stickifyapi.controller.advice;

import com.farumazula.stickifyapi.dto.ExceptionResponse;
import com.farumazula.stickifyapi.exception.AiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Ma1iket
 **/

@Slf4j
@ControllerAdvice
public class AiExceptionHandler {

    @ExceptionHandler(AiException.class)
    public ResponseEntity<ExceptionResponse> handleValidationExceptions(AiException ex) {
        var errors = new ExceptionResponse(ex.getMessage());
        log.error("Ai Exception: {}", errors);
        return ResponseEntity.internalServerError().body(errors);
    }

}
