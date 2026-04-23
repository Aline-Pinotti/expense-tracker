package com.finance_app.expense_tracker.core.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String agencyNo;
    private String code;
    private BigDecimal balance;
    @Column(name = "account_limit")
    private BigDecimal limit;
    private BigDecimal specialLimit; //cheque especial
    private BigDecimal limitBudget; // app resource, to control limit usage
    private BigDecimal cash;

    //TODO: conta conjunta
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant createdAt;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant updatedAt;

    // TODO: getAvailableLimit(), getAvailableBalance() // TODO: Limit - Balance.. debits - credits

    public Account() {
    }

    public Account(UUID id, String name, String agencyNo, String code, Bank bank, BigDecimal balance, BigDecimal limit, BigDecimal specialLimit, BigDecimal limitBudget, BigDecimal cash, User user, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.name = name;
        this.agencyNo = agencyNo;
        this.code = code;
        this.bank = bank;
        this.balance = balance;
        this.limit = limit;
        this.specialLimit = specialLimit;
        this.limitBudget = limitBudget;
        this.cash = cash;
        this.user = user;
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

    public String getAgencyNo() {
        return agencyNo;
    }

    public void setAgencyNo(String agencyNo) {
        this.agencyNo = agencyNo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getLimit() {
        return limit;
    }

    public void setLimit(BigDecimal limit) {
        this.limit = limit;
    }

    public BigDecimal getSpecialLimit() {
        return specialLimit;
    }

    public void setSpecialLimit(BigDecimal specialLimit) {
        this.specialLimit = specialLimit;
    }

    public BigDecimal getLimitBudget() {
        return limitBudget;
    }

    public void setLimitBudget(BigDecimal limitBudget) {
        this.limitBudget = limitBudget;
    }

    public BigDecimal getCash() {
        return cash;
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        Account account = (Account) o;
        return Objects.equals(id, account.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", agencyNo='" + agencyNo + '\'' +
                ", code='" + code + '\'' +
                ", bank=" + bank +
                ", balance=" + balance +
                ", limit=" + limit +
                ", specialLimit=" + specialLimit +
                ", limitBudget=" + limitBudget +
                ", cash=" + cash +
                ", user=" + user +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
