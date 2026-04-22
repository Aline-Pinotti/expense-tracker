package com.finance_app.expense_tracker.core.repositories;

import com.finance_app.expense_tracker.core.entities.Installment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InstallmentRepository extends JpaRepository<Installment, UUID> {
}

