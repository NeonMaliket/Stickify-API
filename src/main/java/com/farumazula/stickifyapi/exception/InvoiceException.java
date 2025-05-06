package com.farumazula.stickifyapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Ma1iket
 **/

@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
public class InvoiceException extends RuntimeException {
    public InvoiceException(String message) {
        super(message);
    }
}
