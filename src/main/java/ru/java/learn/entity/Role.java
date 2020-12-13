package ru.java.learn.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    UNKNOWN,
    USER,
    ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
