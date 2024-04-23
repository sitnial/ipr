package com.iba.ipr.controller.impl;

import com.iba.ipr.controller.UserController;
import com.iba.ipr.dto.request.UserPasswordResetRequest;
import com.iba.ipr.dto.request.UserRequestDto;
import com.iba.ipr.dto.response.SuccessResponse;
import com.iba.ipr.dto.response.UserDto;
import com.iba.ipr.service.AuthenticationService;
import com.iba.ipr.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    @Override
    public UserDto updateUserInfo(UserRequestDto request) {
        return userService.updateUser(request);
    }

    @PreAuthorize("hasAuthority(T(com.iba.ipr.entity.RoleName).ADMIN) || " +
            "hasAuthority(T(com.iba.ipr.entity.RoleName).USER) && " +
            "@authenticationService.authenticationOwnerUser(#id)")
    @Override
    public ResponseEntity<SuccessResponse> changeUserPassword(
            UserPasswordResetRequest userPasswordResetRequest,
            Long id) {
        userService.changeUserPassword(id, userPasswordResetRequest);
        authenticationService.resetAuthentication();
        return ResponseEntity.ok(new SuccessResponse(Boolean.TRUE));
    }
}