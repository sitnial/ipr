package com.iba.ipr.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public record UserRequestDto(
        @NotNull
        @Schema(example = "1")
        @Positive
        Long id,

        @Schema(example = "name")
        String name,

        @Schema(example = "test@gmail.com")
        @Email
        String email) {
}
