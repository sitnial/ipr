package com.iba.ipr.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

@Schema(description = "General error response")
public record GeneralErrorResponse(

        @Schema(example = "2022-04-16T23:07:59.441143700Z")
        Instant dateTime,

        @Schema(example = "Operation completed unsuccessfully")
        String message) {
}