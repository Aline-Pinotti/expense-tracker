package com.finance_app.expense_tracker.core.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_installment")
public class Installment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(columnDefinition = "TEXT")
    private String description;
    private BigDecimal amount;
    private LocalDate paidDay;
    private Integer number;
    private LocalDate dueDate; //if credit-card, it's about the bill

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant createdAt;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant updatedAt;

    @ManyToOne
    @JoinColumn(name = "transaction_id",
                foreignKey = @ForeignKey(name = "fk_installment_transaction"))
    private Transaction transaction;

    // TODO: method generate next installment (batch) - validate if next isn't generated yet, if there is nothing manually registered
    // TODO: method generate all installments (user request) - also validates


    public Installment() {
    }

    public Installment(UUID id, String description, BigDecimal amount, LocalDate paidDay, Integer number, LocalDate dueDate, Instant createdAt, Instant updatedAt, Transaction transaction) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.paidDay = paidDay;
        this.number = number;
        this.dueDate = dueDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.transaction = transaction;
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

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Installment that = (Installment) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Installment{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", paidDay=" + paidDay +
                ", number=" + number +
                ", dueDate=" + dueDate +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", transaction=" + transaction +
                '}';
    }
}
