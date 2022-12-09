package com.tehnology.kkt.models.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    Manager,
    Client,
    Administrator;

    @Override
    public String getAuthority() {
        return name();
    }
}
