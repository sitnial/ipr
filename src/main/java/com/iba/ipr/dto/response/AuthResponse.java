package com.iba.ipr.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "Authorization response")
public record AuthResponse(
        String accessToken) {
}