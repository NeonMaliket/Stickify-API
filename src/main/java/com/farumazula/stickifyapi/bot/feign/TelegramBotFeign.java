package com.farumazula.stickifyapi.bot.feign;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(
        name = "telegramBotFeign",
        url = "https://api.telegram.org"
)
public interface TelegramBotFeign {

    @GetMapping(value = "/file/bot{token}/{filePath}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    Response downloadFile(
            @PathVariable("token") String botToken,
            @PathVariable("filePath") String filePath
    );
}
