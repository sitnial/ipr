package com.iba.ipr.security;

import com.iba.ipr.entity.RoleName;
import com.iba.ipr.entity.User;
import com.iba.ipr.exception.badrequest.UserNotFoundException;
import com.iba.ipr.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.iba.ipr.util.JwtConstants.*;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFactory {

    private final UserRepository userRepository;

    public JwtAuthentication create(Claims claims) {
        JwtAuthentication authentication = new JwtAuthentication();
        String email = claims.get(TOKEN_CLAIM_EMAIL, String.class);
        authentication.setUserEmail(email);
        authentication.setUserName(claims.get(TOKEN_CLAIM_USERNAME, String.class));
        authentication.setRoles(getRoles(claims));
        authentication.setUserId(getUserId(email));
        authentication.setAuthenticated(Boolean.TRUE);
        return authentication;
    }

    private Set<RoleName> getRoles(Claims claims) {
        List<String> roles = claims.get(TOKEN_CLAIM_ROLES, List.class);
        return roles.stream()
                .map(RoleName::valueOf)
                .collect(Collectors.toSet());
    }

    private Long getUserId(String email) {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
        return user.getId();
    }
}