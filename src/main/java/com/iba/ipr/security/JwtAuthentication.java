package com.iba.ipr.security;

import com.iba.ipr.entity.RoleName;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.util.Pair;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Set;

@Getter
@Setter
public class JwtAuthentication implements Authentication {

    private boolean isAuthenticated;

    private Long userId;

    private String userName;

    private String userEmail;

    private Set<RoleName> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return Pair.of(userEmail, userName);
    }

    @Override
    public Object getPrincipal() {
        return userEmail;
    }

    @Override
    public boolean isAuthenticated() {
        return false;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.isAuthenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return userName;
    }

    public boolean isAdmin() {
        return roles.contains(RoleName.ADMIN);
    }

    public boolean isUser() {
        return roles.contains(RoleName.USER);
    }
}
