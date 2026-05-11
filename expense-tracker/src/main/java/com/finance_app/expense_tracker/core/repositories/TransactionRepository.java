package com.finance_app.expense_tracker.core.repositories;

import com.finance_app.expense_tracker.core.entities.Transaction;
import com.finance_app.expense_tracker.core.projections.TransactionProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    @Query(nativeQuery = true, value = """
            SELECT t.id, t.description, t.amount, t.date, t.fixed, t.due_date AS dueDate, t.paid_date AS paidDate,
                   t.in_out AS inOut, t.number_of_installments AS numberOfInstallments, tt.name AS type,
                   mc.name AS mainCategory, pm.name AS paymentMethod, t.user_id AS userId,
                   t.account_id AS accountId, t.credit_card_bill_id AS creditCardBillId
            FROM transactions t
            INNER JOIN transaction_types tt ON tt.id = t.transaction_type_id
            INNER JOIN main_categories mc ON mc.id = t.main_category_id
            INNER JOIN payment_methods pm ON pm.id = t.payment_method_id
            """)
    Page<TransactionProjection> searchAllPaged(Pageable pageable);
}

