package com.iba.ipr.service;

import com.iba.ipr.security.JwtAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    public JwtAuthentication getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (authentication instanceof JwtAuthentication)
                ? (JwtAuthentication) authentication
                : null;
    }

    public void resetAuthentication() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }
    public boolean authenticationOwnerUser(@NotNull Long userId) {
        JwtAuthentication authentication = getAuthentication();
        return (Objects.nonNull(authentication)) ? userId.equals(authentication.getUserId()) : Boolean.FALSE;
    }
}
