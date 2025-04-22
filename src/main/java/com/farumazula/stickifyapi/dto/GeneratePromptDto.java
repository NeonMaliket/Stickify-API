package com.farumazula.stickifyapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * @author Ma1iket
 **/

public record GeneratePromptDto(

        @Size(
                min = 3,
                max = 100,
                message = "Prompt size must be between 3 and 100")
        @NotBlank(message = "Prompt can`t be empty")
        String prompt
) {
}
