package com.iba.ipr.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@RequiredArgsConstructor
@SuperBuilder
@Getter
@Entity
@Table(name = "roles")
public class Role extends BaseEntity {
    @Column(name = "role_name", length = 5, nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private RoleName roleName;
}