package com.finance_app.expense_tracker.application.dtos;

import com.finance_app.expense_tracker.core.entities.Account;

import java.math.BigDecimal;
import java.util.UUID;

public class AccountDTO {
    private UUID id;
    private String name;
    private String agencyNo;
    private String code;
    private BigDecimal balance;
    private BigDecimal limit;
    private BigDecimal specialLimit; //cheque especial
    private BigDecimal limitBudget; // app resource, to control limit usage
    private BigDecimal cash;
    private UserDTO user;
    private BankDTO bank;

    public AccountDTO() {
    }

    public AccountDTO(Account entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.agencyNo = entity.getAgencyNo();
        this.code = entity.getCode();
        this.balance = entity.getBalance();
        this.limit = entity.getLimit();
        this.specialLimit = entity.getSpecialLimit();
        this.limit = entity.getLimitBudget();
        this.cash = entity.getCash();
        this.user = new UserDTO(entity.getUser());
        this.bank = new BankDTO(entity.getBank());
    }

    public AccountDTO(UUID id, String name, String agencyNo, String code, BigDecimal balance, BigDecimal limit, BigDecimal specialLimit, BigDecimal limitBudget, BigDecimal cash, UserDTO user, BankDTO bank) {
        this.id = id;
        this.name = name;
        this.agencyNo = agencyNo;
        this.code = code;
        this.balance = balance;
        this.limit = limit;
        this.specialLimit = specialLimit;
        this.limitBudget = limitBudget;
        this.cash = cash;
        this.user = user;
        this.bank = bank;
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

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public BankDTO getBank() {
        return bank;
    }

    public void setBank(BankDTO bank) {
        this.bank = bank;
    }
}
