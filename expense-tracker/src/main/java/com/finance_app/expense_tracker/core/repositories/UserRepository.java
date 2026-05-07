package com.finance_app.expense_tracker.core.repositories;

import com.finance_app.expense_tracker.core.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    public Page<User> findByUsernameContaining(String username, Pageable pageable);
    public Optional<User> findByEmail(String email);
}

