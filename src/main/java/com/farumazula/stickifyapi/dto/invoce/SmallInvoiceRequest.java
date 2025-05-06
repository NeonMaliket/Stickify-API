package com.farumazula.stickifyapi.dto.invoce;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record SmallInvoiceRequest(
        @NotBlank(message = "Chat id can`t be empty")
        @JsonProperty("chat_id")
        String chatId,
        @NotBlank(message = "Prompt can`t be empty")
        String prompt
) {
}
