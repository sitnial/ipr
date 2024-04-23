package com.iba.ipr.controller.impl;

import com.iba.ipr.controller.AuthController;
import com.iba.ipr.dto.request.LoginAndPasswordDto;
import com.iba.ipr.dto.response.AuthResponse;
import com.iba.ipr.entity.User;
import com.iba.ipr.security.JwtProvider;
import com.iba.ipr.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {
    private final UserService userService;
    private final JwtProvider jwtProvider;


    @Override
    public AuthResponse authorizationWithLoginAndPassword(@RequestBody LoginAndPasswordDto loginAndPasswordDto) {
        User user = userService.validateUser(loginAndPasswordDto);
        String jwtToken = jwtProvider.generateToken(user);

        return new AuthResponse(jwtToken);
    }
}