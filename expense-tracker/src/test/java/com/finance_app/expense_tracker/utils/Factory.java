package com.finance_app.expense_tracker.utils;

import com.finance_app.expense_tracker.application.dtos.BankDTO;
import com.finance_app.expense_tracker.core.entities.*;
import com.finance_app.expense_tracker.core.enums.CreditCardBillStatus;
import com.finance_app.expense_tracker.core.enums.TransactionCategory;
import com.finance_app.expense_tracker.core.enums.TransactionPaymentMethod;
import com.finance_app.expense_tracker.core.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;

public class Factory {

    // ============ Bank ============
    public static Bank createBank() {
        Bank bank = new Bank();
        bank.setName("Nubank");
        bank.setNumber(260);
        bank.setColorLabel("#f3f3f3");
        return bank;
    }

    public static BankDTO createBankDTO() {
        return new BankDTO(createBank());
    }

    // ============ User ============
    public static User createUser() { //TODO: create a User with and without authentication
        User user = new User();
        user.setUsername("testuser");
        user.setEmail("testuser@example.com");
        user.setPassword("password123");
        return user;
    }

    // ============ Account ============
    public static Account createAccount() {
        Account account = new Account();
        account.setName("Test Account");
        account.setAgencyNo("0001");
        account.setCode("123456");
        account.setBalance(new BigDecimal("5000.00"));
        account.setLimit(new BigDecimal("2000.00"));
        account.setSpecialLimit(new BigDecimal("500.00"));
        account.setLimitBudget(new BigDecimal("2000.00"));
        account.setCash(new BigDecimal("1000.00"));
        account.setBank(createBank());
        account.setUser(createUser());
        return account;
    }

    // ============ Role ============
    public static Role createRole() {
        Role role = new Role();
        role.setAuthority("ROLE_USER");
        return role;
    }

    // ============ CreditCard ============
    public static CreditCard createCreditCard() { //TODO: create creditcart international, national, with limit, withoutlimit etc
        CreditCard creditCard = new CreditCard();
        creditCard.setName("Test Credit Card");
        creditCard.setNumber(1234);
        creditCard.setLimit(new BigDecimal("5000.00"));
        creditCard.setClosingDay(10);
        creditCard.setDueDay(20);
        creditCard.setExpirationDate(YearMonth.of(2026, 12));
        creditCard.setAccount(createAccount());
        creditCard.setInternational(true);
        return creditCard;
    }

    // ============ CreditCardBill ============
    public static CreditCardBill createCreditCardBill() {//TODO: expired, closed etc
        CreditCardBill bill = new CreditCardBill();
        bill.setCard(createCreditCard());
        bill.setDueDate(LocalDate.now().plusDays(10));
        bill.setClosingDate(LocalDate.now());
        bill.setAmount(new BigDecimal("500.00"));
        bill.setStatus(CreditCardBillStatus.OPEN);
        return bill;
    }

    // ============ Transaction ============
    public static Transaction createTransaction() {
        Transaction transaction = new Transaction();
        transaction.setDescription("Test Transaction");
        transaction.setAmount(new BigDecimal("100.00"));
        transaction.setDate(LocalDate.now());
        transaction.setFixed(false);
        transaction.setDueDate(LocalDate.now().plusDays(5));
        transaction.setInOut("OUT"); // TODO
        transaction.setNumberOfInstallments(1);
        transaction.setType(TransactionType.EXPENSE);
        transaction.setMainCategory(TransactionCategory.FOOD);
        transaction.setPaymentMethod(TransactionPaymentMethod.DEBIT_CARD);
        transaction.setUser(createUser());
        transaction.setAccount(createAccount());
        return transaction;
    }

    // ============ Installment ============
    public static Installment createInstallment() {
        Installment installment = new Installment();
        installment.setDescription("Test Installment");
        installment.setAmount(new BigDecimal("50.00"));
        installment.setNumber(1);
        installment.setDueDate(LocalDate.now().plusDays(5));
        installment.setTransaction(createTransaction());
        return installment;
    }

    // ============ Notification ============
    public static Notification createNotification() {
        Notification notification = new Notification();
        notification.setSubject("Test Notification");
        notification.setMessage("This is a test notification message");
        notification.setDateTime(LocalDateTime.now());
        notification.setUser(createUser());
        return notification;
    }
}
