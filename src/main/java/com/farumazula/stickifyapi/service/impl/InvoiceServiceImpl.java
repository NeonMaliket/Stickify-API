package com.farumazula.stickifyapi.service.impl;

import com.farumazula.stickifyapi.bot.feign.TelegramBotFeign;
import com.farumazula.stickifyapi.dto.invoce.InvoiceLinkResponse;
import com.farumazula.stickifyapi.dto.invoce.InvoiceRequest;
import com.farumazula.stickifyapi.dto.invoce.LabeledPrice;
import com.farumazula.stickifyapi.dto.invoce.SmallInvoiceRequest;
import com.farumazula.stickifyapi.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Ma1iket
 **/

@Slf4j
@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final TelegramBotFeign telegramBotFeign;

    @Override
    public Optional<InvoiceLinkResponse> createInvoiceLink(SmallInvoiceRequest invoiceRequest) {
        log.info("'Service' Creating invoice: {}", invoiceRequest);
        var fullInvoiceRequest = InvoiceRequest.builder()
                .title("Generating Sticker with AI")
                .description(MessageFormat.format("For generating: {0}", invoiceRequest.prompt()))
                .payload(UUID.randomUUID().toString())
                .chatId(invoiceRequest.chatId())
                .currency("XTR")
                .prices(List.of(
                        new LabeledPrice(
                                invoiceRequest.prompt(), 100
                        )
                ))
                .build();

        return Optional.ofNullable(telegramBotFeign.createInvoiceLink(fullInvoiceRequest));
    }
}
