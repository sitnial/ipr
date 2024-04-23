package com.iba.ipr.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

public record UserDto(
        @NotNull
        @Schema(example = "1")
        int id,
        @NotNull
        @Schema(example = "test@gmail.com")
        String email,
        @NotNull
        @Schema(example = "Test User")
        String name) {
}
