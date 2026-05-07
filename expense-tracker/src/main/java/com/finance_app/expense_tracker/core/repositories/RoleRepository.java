package com.finance_app.expense_tracker.core.repositories;

import com.finance_app.expense_tracker.core.entities.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

    public Page<Role> findByAuthorityContaining(String authority, Pageable pageable);
}

