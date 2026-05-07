package com.finance_app.expense_tracker.application.dtos;

import com.finance_app.expense_tracker.core.entities.Notification;
import com.finance_app.expense_tracker.core.entities.Role;
import com.finance_app.expense_tracker.core.entities.User;

import java.time.Instant;
import java.util.*;

public class UserDTO {
    private UUID id;
    private String username, email;
    private Set<Role> roles = new HashSet<>();

    public UserDTO() {
    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }

    public UserDTO(UUID id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
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
