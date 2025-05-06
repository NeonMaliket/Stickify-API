package com.farumazula.stickifyapi.controller.advice;

import com.farumazula.stickifyapi.dto.ExceptionResponse;
import com.farumazula.stickifyapi.exception.InvoiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Ma1iket
 **/

@Slf4j
@ControllerAdvice
public class InvoiceExceptionHandler {

    @ExceptionHandler(InvoiceException.class)
    public ResponseEntity<ExceptionResponse> handleValidationExceptions(InvoiceException ex) {
        var errors = new ExceptionResponse(ex.getMessage());
        log.error("Invoice Exception: {}", errors);
        return ResponseEntity.unprocessableEntity().body(errors);
    }

}
