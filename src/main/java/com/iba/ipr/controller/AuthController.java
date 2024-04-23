package com.iba.ipr.controller;

import com.iba.ipr.dto.request.LoginAndPasswordDto;
import com.iba.ipr.dto.response.AuthResponse;
import com.iba.ipr.dto.response.GeneralErrorResponse;
import com.iba.ipr.dto.response.ResponseMessageConstant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "authorization", description = "The authorization API")
@RequestMapping("/api/v1/authorization")
public interface AuthController {

    @Operation(summary = "Login and get jwt token")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully logged in system",
                    content = @Content(schema = @Schema(implementation = AuthResponse.class))
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden - forbidden access",
                    content = @Content(schema = @Schema(implementation = GeneralErrorResponse.class),
                            examples = @ExampleObject(ResponseMessageConstant.INCORRECT_CREDENTIALS))
            )
    })
    @PostMapping
    AuthResponse authorizationWithLoginAndPassword(LoginAndPasswordDto loginAndPasswordDto);
}