package com.farumazula.stickifyapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Ma1iket
 **/
@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class TelegramBotException extends RuntimeException {

    public TelegramBotException(String message) {
        super(message);
    }

    public TelegramBotException(Throwable cause) {
        super(cause);
    }
}
