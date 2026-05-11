package com.finance_app.expense_tracker.application.dtos;

import com.finance_app.expense_tracker.core.entities.Installment;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class InstallmentDTO {

    private UUID id;
    private String description;
    private BigDecimal amount;
    private LocalDate paidDay;
    private Integer number;
    private LocalDate dueDate; //if credit-card, it's about the bill
    private TransactionDTO transaction;

    public InstallmentDTO() {
    }

    public InstallmentDTO(Installment entity) {
        this.id = entity.getId();
        this.description = entity.getDescription();
        this.amount = entity.getAmount();
        this.paidDay = entity.getPaidDay();
        this.number = entity.getNumber();
        this.dueDate = entity.getDueDate();
        this.transaction = new TransactionDTO(entity.getTransaction());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getPaidDay() {
        return paidDay;
    }

    public void setPaidDay(LocalDate paidDay) {
        this.paidDay = paidDay;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public TransactionDTO getTransaction() {
        return transaction;
    }

    public void setTransaction(TransactionDTO transaction) {
        this.transaction = transaction;
    }
}
