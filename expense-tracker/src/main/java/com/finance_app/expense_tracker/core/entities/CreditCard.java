package com.finance_app.expense_tracker.core.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.YearMonth;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_credit_card")
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(columnDefinition = "TEXT")
    private String name;
    private Integer number; // four last number for better identification, or complete if wanted, but completely optional
    @Column(name = "card_limit")
    private BigDecimal limit;
    private int closingDay;
    private int dueDay;
    private YearMonth expirationDate;
    private Boolean isInternational;

    @ManyToOne
    @JoinColumn(name = "account_id",
                foreignKey = @ForeignKey(name = "fk_credit_card_account"))
    private Account account; // pode ser nulo, ser for fintech/empresa só de cartão, mas então precisa vincular com usuário
    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    private List<CreditCardBill> bills;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant createdAt;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant updatedAt;

    //TODO: getAvailableLimit()

    public CreditCard() {
    }

    public CreditCard(UUID id, String name, Integer number, BigDecimal limit, int closingDay, int dueDay, YearMonth expirationDate, Account account, Boolean isInternational, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.limit = limit;
        this.closingDay = closingDay;
        this.dueDay = dueDay;
        this.expirationDate = expirationDate;
        this.account = account;
        this.isInternational = isInternational;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public BigDecimal getLimit() {
        return limit;
    }

    public void setLimit(BigDecimal limit) {
        this.limit = limit;
    }

    public int getClosingDay() {
        return closingDay;
    }

    public void setClosingDay(int closingDay) {
        this.closingDay = closingDay;
    }

    public int getDueDay() {
        return dueDay;
    }

    public void setDueDay(int dueDay) {
        this.dueDay = dueDay;
    }

    public YearMonth getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(YearMonth expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Boolean getInternational() {
        return isInternational;
    }

    public void setInternational(Boolean international) {
        isInternational = international;
    }

   public List<CreditCardBill> getBills() {
        return bills;
    }

    public void setBills(List<CreditCardBill> bills) {
        this.bills = bills;
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
        CreditCard that = (CreditCard) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", limit=" + limit +
                ", closingDay=" + closingDay +
                ", dueDay=" + dueDay +
                ", expirationDate=" + expirationDate +
                ", account=" + account +
                ", isInternational=" + isInternational +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
