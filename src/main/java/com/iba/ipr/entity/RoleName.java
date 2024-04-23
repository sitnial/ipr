package com.iba.ipr.entity;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public enum RoleName implements GrantedAuthority {
    USER("USER"),
    ADMIN("ADMIN");

    private final String roleName;

    @Override
    public String getAuthority() {
        return roleName;
    }
}
