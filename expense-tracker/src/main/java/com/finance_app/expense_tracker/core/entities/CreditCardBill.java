package com.finance_app.expense_tracker.core.entities;

import com.finance_app.expense_tracker.core.enums.CreditCardBillStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_credit_card_bill")
public class CreditCardBill {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private LocalDate dueDate;
    private LocalDate closingDate;
    private BigDecimal amount;
    private LocalDate paidDate;
    private BigDecimal amountPayed; // para pagamentos parciais
    private CreditCardBillStatus status;

    @ManyToOne
    @JoinColumn(name = "credit_card_id")
    private CreditCard card;
    @OneToMany(mappedBy = "billing", cascade = CascadeType.ALL)
    private List<Transaction> transactions = new ArrayList<>();

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant createdAt;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant updatedAt;


    // TODO: get para hisórico de faturas, calcular juros e encargos
    // TODO: importBill() - files exported from bank apps
    // TODO: generateBill() e/ou closeBill()


    public CreditCardBill() {
    }

    public CreditCardBill(UUID id, LocalDate dueDate, LocalDate closingDate, BigDecimal amount, LocalDate paidDate, BigDecimal amountPayed, CreditCardBillStatus status, CreditCard card, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.dueDate = dueDate;
        this.closingDate = closingDate;
        this.amount = amount;
        this.paidDate = paidDate;
        this.amountPayed = amountPayed;
        this.status = status;
        this.card = card;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDate getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(LocalDate paidDate) {
        this.paidDate = paidDate;
    }

    public CreditCard getCard() {
        return card;
    }

    public void setCard(CreditCard card) {
        this.card = card;
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

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CreditCardBill that = (CreditCardBill) o;
        return Objects.equals(card, that.card);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(card);
    }

    @Override
    public String toString() {
        return "CreditCardBill{" +
                "id=" + id +
                ", dueDate=" + dueDate +
                ", closingDate=" + closingDate +
                ", amount=" + amount +
                ", paidDate=" + paidDate +
                ", amountPayed=" + amountPayed +
                ", status=" + status +
                ", card=" + card +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
