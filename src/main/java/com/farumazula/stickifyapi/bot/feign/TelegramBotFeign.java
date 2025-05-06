package com.farumazula.stickifyapi.bot.feign;

import com.farumazula.stickifyapi.config.TelegramFeignConfig;
import com.farumazula.stickifyapi.dto.invoce.InvoiceLinkResponse;
import com.farumazula.stickifyapi.dto.invoce.InvoiceRequest;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "telegramBotFeign",
        url = "https://api.telegram.org",
        configuration = TelegramFeignConfig.class
)
public interface TelegramBotFeign {

    @GetMapping(value = "/file/bot@token@/{filePath}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    Response downloadFile(
            @PathVariable("filePath") String filePath
    );

    @PostMapping("/bot@token@/createInvoiceLink")
    InvoiceLinkResponse createInvoiceLink(
            @RequestBody InvoiceRequest request
    );

}
