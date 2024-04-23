package com.iba.ipr.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Success response")
public record SuccessResponse(
        Boolean success) {
}