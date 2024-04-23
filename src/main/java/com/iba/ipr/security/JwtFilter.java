package com.iba.ipr.security;

import io.jsonwebtoken.Claims;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@RequiredArgsConstructor
@Component
public class JwtFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION = "Authorization";
    private static final String TOKEN_HEADER = "Bearer ";

    private final JwtAuthenticationFactory jwtAuthenticationFactory;
    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String jwtToken = getTokenFromRequest(request);
        JwtAuthentication currentAuthentication =
                (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
        if (Objects.isNull(currentAuthentication) && Objects.nonNull(jwtToken)) {
            if(isValidToken(jwtToken)) {
                JwtAuthentication authentication = generateAuthentication(jwtToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

    protected boolean isValidToken(@NonNull String token) {
        return jwtProvider.validateToken(token);
    }
    protected Claims getTokenClaims(@NonNull String jwtToken) {
        return jwtProvider.getClaims(jwtToken);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String headerAuthorization = request.getHeader(AUTHORIZATION);
        if (StringUtils.hasText(headerAuthorization) && headerAuthorization.startsWith(TOKEN_HEADER)) {
            return headerAuthorization.substring(TOKEN_HEADER.length());
        }
        return null;
    }

    private JwtAuthentication generateAuthentication(String jwtToken) {
        Claims claims = getTokenClaims(jwtToken);
        return jwtAuthenticationFactory.create(claims);
    }
}