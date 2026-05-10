package com.finance_app.expense_tracker.core.repositories;

import com.finance_app.expense_tracker.core.entities.CreditCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

public interface CreditCardRepository extends JpaRepository<CreditCard, UUID> {

    Page<CreditCard>  findByAccountId(UUID accountId, Pageable pageable);

    Page<CreditCard>  findByAccountUserId(UUID userId, Pageable pageable);
}

