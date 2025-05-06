package com.farumazula.stickifyapi.dto.invoce;

public record LabeledPrice(
        String label,
        Integer amount
) {
}
