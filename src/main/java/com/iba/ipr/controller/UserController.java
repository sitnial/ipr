package com.iba.ipr.controller;

import com.iba.ipr.dto.request.UserPasswordResetRequest;
import com.iba.ipr.dto.request.UserRequestDto;
import com.iba.ipr.dto.response.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/api/v1/users")
@Tag(name = "authorization", description = "The authorization API")
public interface UserController {

    @Operation(summary = "Get info about user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User has founded",
                    content = @Content(schema = @Schema(implementation = AuthResponse.class))
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden - forbidden access",
                    content = @Content(schema = @Schema(implementation = GeneralErrorResponse.class),
                            examples = @ExampleObject(ResponseMessageConstant.USER_WAS_NOT_FOUND))
            )
    })
    @PutMapping("/users")
    UserDto updateUserInfo(@RequestBody @Valid UserRequestDto request);

    @PutMapping("/{id}/password")
    ResponseEntity<SuccessResponse> changeUserPassword(@RequestBody @Valid UserPasswordResetRequest userPasswordResetRequest, @PathVariable Long id);
}
