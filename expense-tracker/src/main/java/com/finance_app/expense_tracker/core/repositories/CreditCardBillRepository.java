package com.finance_app.expense_tracker.core.repositories;

import com.finance_app.expense_tracker.core.entities.CreditCardBill;
import com.finance_app.expense_tracker.core.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.YearMonth;
import java.util.List;
import java.util.UUID;

@Repository
public interface CreditCardBillRepository extends JpaRepository<CreditCardBill, UUID> {
    @Query("SELECT t FROM Transaction t " +
           "JOIN CreditCardBill ccb ON t.billing = ccb " +
           "WHERE YEAR(ccb.dueDate) = :year " +
           "AND MONTH(ccb.dueDate) = :month " +
           "AND t.dueDate = ccb.dueDate")
    List<Transaction> findTransactionsByBillingMonth(@Param("year") int year, @Param("month") int month);
}

