package com.finance_app.expense_tracker.core.repositories;

import com.finance_app.expense_tracker.core.entities.CreditCardBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CreditCardBillRepository extends JpaRepository<CreditCardBill, UUID> {
}

