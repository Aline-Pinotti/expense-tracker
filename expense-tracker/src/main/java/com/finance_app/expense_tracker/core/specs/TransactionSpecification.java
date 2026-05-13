package com.finance_app.expense_tracker.core.specs;

import com.finance_app.expense_tracker.application.filters.TransactionFilter;
import com.finance_app.expense_tracker.core.entities.Transaction;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.UUID;

public class TransactionSpecification {

    public static Specification<Transaction> from(TransactionFilter f) {
        // TODO: All of vs Any of (not all are both and/or)
        // TODO: use AnyOf and create a new predicate for when is all of them
        return Specification.anyOf(
                byUser(f.userId()),
                byCreditCardBill(f.creditCardBillId()),
                byBillingMonth(f.billingMonth()),
                byDueDate(f.dueDate()),
                byDueDateBetween(f.fromDate(), f.toDate()),
                byDescription(f.description())
                );
    }

    // TODO: predicate to receive wich date and periodo (initial and final date) to get a range in dueDate ou date

    private static Specification<Transaction> byUser(UUID userId) {
        return (root, query, cb) ->
                userId == null ? null : cb.equal(root.get("user").get("id"), userId);
    }

    private static Specification<Transaction> byCreditCardBill(UUID creditCardBillId) {
        return (root, query, cb) ->
                creditCardBillId == null ? null : cb.equal(root.get("creditCardBill").get("id"), creditCardBillId);
    }

    private static Specification<Transaction> byBillingMonth(YearMonth billingMonth) {
        return (root, query, cb) -> {
            if (billingMonth == null) return null;
            LocalDate start = billingMonth.atDay(1);
            LocalDate end = billingMonth.atEndOfMonth();
            return cb.between(root.get("dueDate"), start, end);
        };
    }

    private static Specification<Transaction> byDueDate(LocalDate dueDate) {
        return (root, query, cb) ->
                dueDate == null ? null : cb.equal(root.get("dueDate"), dueDate);
    }

    private static Specification<Transaction> byDueDateBetween(LocalDate from, LocalDate to) {
        return (root, query, cb) ->
                (from == null || to == null) ? null : cb.between(root.get("dueDate"), from, to);
    }

    private static Specification<Transaction> byDescription(String description) {
        return (root, query, cb) ->
                description == null ? null : cb.like(cb.lower(root.get("description")), "%" + description.toLowerCase() + "%");
    }
}
