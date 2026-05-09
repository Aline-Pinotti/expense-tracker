package com.finance_app.expense_tracker.application.dtos;

import com.finance_app.expense_tracker.core.entities.Role;
import com.finance_app.expense_tracker.core.entities.User;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class UserRoleDTO {
    // TODO: changing (revoking or adding new) role to use

    private UUID id;
    private String username, email;
    private Set<Role> roles = new HashSet<>();

    public UserRoleDTO() {
    }

    public UserRoleDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.roles = user.getRoles();
    }

    public UserRoleDTO(UUID id, String username, String email, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles.addAll(roles);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
