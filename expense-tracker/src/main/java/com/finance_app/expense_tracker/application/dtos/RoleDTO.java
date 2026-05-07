package com.finance_app.expense_tracker.application.dtos;

import com.finance_app.expense_tracker.core.entities.Role;

import java.util.UUID;

public class RoleDTO {
    private UUID id;
    private String authority;

    public RoleDTO() {
    }

    public RoleDTO(Role role) {
        this.id = role.getId();
        this.authority = role.getAuthority();
    }

    public RoleDTO(UUID id, String authority) {
        this.id = id;
        this.authority = authority;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
