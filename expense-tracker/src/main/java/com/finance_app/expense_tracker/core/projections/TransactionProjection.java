package com.finance_app.expense_tracker.core.projections;

import java.time.LocalDate;
import java.util.UUID;

public interface TransactionProjection {
    UUID getId();
    String getDescription();
    String getAmount();
    String getDate();
    Boolean isFixed();
    LocalDate getDueDate();
    LocalDate getPaidDate();
    String getInOut();
    Integer getNumberOfInstallments();
    String getType();
    String getMainCategory();
    String getPaymentMethod();
    UUID getUserId();
    UUID getAccountId();
    UUID getCreditCardBillId();
}
