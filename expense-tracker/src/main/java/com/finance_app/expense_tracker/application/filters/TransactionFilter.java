package com.finance_app.expense_tracker.application.filters;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.UUID;

public record TransactionFilter(
    LocalDate dueDate,
    YearMonth billingMonth,
    UUID creditCardBillId,
    UUID userId,
    LocalDate fromDate,
    LocalDate toDate,
    String description
) {}
