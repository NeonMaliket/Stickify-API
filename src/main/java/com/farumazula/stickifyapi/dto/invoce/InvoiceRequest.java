package com.farumazula.stickifyapi.dto.invoce;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

@Builder
public record InvoiceRequest(
        @JsonProperty("chat_id")
        String chatId,
        String title,
        String description,
        String payload,
        String currency,
        List<LabeledPrice> prices,
        @JsonProperty("need_name")
        Boolean needName
) {
}
