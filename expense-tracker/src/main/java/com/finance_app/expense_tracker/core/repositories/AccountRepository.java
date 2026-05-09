package com.finance_app.expense_tracker.core.repositories;

import com.finance_app.expense_tracker.core.entities.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
    Page<Account> findByUserId(UUID userId, Pageable pageable);
}

