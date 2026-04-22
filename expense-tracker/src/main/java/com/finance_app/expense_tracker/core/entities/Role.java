package com.finance_app.expense_tracker.core.entities;

import com.finance_app.expense_tracker.core.enums.RoleAuthority;

import java.util.UUID;

public class Role {
    private UUID id;
    //private RoleAuthority authority; //lembrando que tem que gravar como texto!!
    private String authority;

    public Role() {
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
