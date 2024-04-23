package com.iba.ipr.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

@Schema(description = "User credentials")
public record LoginAndPasswordDto(
        @NotNull
        @Schema(example = "admin")
        String email,
        @NotNull
        @Schema(example = "admin")
        String password) {
}