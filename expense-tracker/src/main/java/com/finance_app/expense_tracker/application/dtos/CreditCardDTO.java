package com.finance_app.expense_tracker.application.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.finance_app.expense_tracker.core.entities.CreditCard;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.UUID;

public class CreditCardDTO {
    private UUID id;
    private String name;
    private Integer number; // four last number for better identification, or complete if wanted, but completely optional
    private BigDecimal limit;
    private int closingDay;
    private int dueDay;
    private YearMonth expirationDate;
    private Boolean isInternational;
    private AccountDTO account;

    public CreditCardDTO() {
    }

    public CreditCardDTO(CreditCard entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.number = entity.getNumber();
        this.limit = entity.getLimit();
        this.closingDay = entity.getClosingDay();
        this.dueDay = entity.getDueDay();
        this.expirationDate = entity.getExpirationDate();
        this.isInternational = entity.isInternational();
        this.account = new AccountDTO(entity.getAccount());
    }

    public CreditCardDTO(UUID id, String name, Integer number, BigDecimal limit, int closingDay, int dueDay, YearMonth expirationDate, Boolean isInternational, AccountDTO account) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.limit = limit;
        this.closingDay = closingDay;
        this.dueDay = dueDay;
        this.expirationDate = expirationDate;
        this.isInternational = isInternational;
        this.account = account;
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

    public Boolean isInternational() {
        return isInternational;
    }

    public void setInternational(Boolean international) {
        isInternational = international;
    }

    public AccountDTO getAccount() {
        return account;
    }

    public void setAccount(AccountDTO account) {
        this.account = account;
    }
}
