package com.finance_app.expense_tracker.application.dtos;

import com.finance_app.expense_tracker.core.entities.CreditCardBill;
import com.finance_app.expense_tracker.core.enums.CreditCardBillStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class CreditCardBillDTO {
    private UUID id;
    private LocalDate dueDate;
    private LocalDate closingDate;
    private BigDecimal amount;
    private LocalDate paidDate;
    private BigDecimal amountPayed; // para pagamentos parciais
    private CreditCardBillStatus status;
    private CreditCardDTO card;

    public CreditCardBillDTO() {
    }

    public CreditCardBillDTO(CreditCardBill entity) {
        this.id = entity.getId();
        this.dueDate = entity.getDueDate();
        this.closingDate = entity.getClosingDate();
        this.amount = entity.getAmount();
        this.paidDate = entity.getPaidDate();
        this.amountPayed = entity.getAmountPayed();
        this.status = entity.getStatus();
        this.card = new CreditCardDTO(entity.getCard());
    }

    public CreditCardBillDTO(UUID id, LocalDate dueDate, LocalDate closingDate, BigDecimal amount, LocalDate paidDate, BigDecimal amountPayed, CreditCardBillStatus status, CreditCardDTO card) {
        this.id = id;
        this.dueDate = dueDate;
        this.closingDate = closingDate;
        this.amount = amount;
        this.paidDate = paidDate;
        this.amountPayed = amountPayed;
        this.status = status;
        this.card = card;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(LocalDate closingDate) {
        this.closingDate = closingDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(LocalDate paidDate) {
        this.paidDate = paidDate;
    }

    public BigDecimal getAmountPayed() {
        return amountPayed;
    }

    public void setAmountPayed(BigDecimal amountPayed) {
        this.amountPayed = amountPayed;
    }

    public CreditCardBillStatus getStatus() {
        return status;
    }

    public void setStatus(CreditCardBillStatus status) {
        this.status = status;
    }

    public CreditCardDTO getCard() {
        return card;
    }

    public void setCard(CreditCardDTO card) {
        this.card = card;
    }
}
