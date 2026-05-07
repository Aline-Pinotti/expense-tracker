package com.finance_app.expense_tracker.utils;

import com.finance_app.expense_tracker.core.entities.*;
import com.finance_app.expense_tracker.core.enums.CreditCardBillStatus;
import com.finance_app.expense_tracker.core.enums.TransactionCategory;
import com.finance_app.expense_tracker.core.enums.TransactionPaymentMethod;
import com.finance_app.expense_tracker.core.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ObjectFactory {
    // ============ Bank ============
    public static List<Bank> createBanks() {
        List<Bank> banks = new ArrayList<>();
        // banks.add(new Bank(UUID.randomUUID(), "Nubank", 260, "#f3f3f3")); // reserved for test
        banks.add(new Bank(UUID.randomUUID(), "Banco do Brasil", 1, "#fdb913"));
        banks.add(new Bank(UUID.randomUUID(), "Bradesco", 237, "#ff0000"));
        banks.add(new Bank(UUID.randomUUID(), "Caixa Econômica Federal", 104, "#0033a0"));
        banks.add(new Bank(UUID.randomUUID(), "Itaú", 341, "#ff6600"));
        banks.add(new Bank(UUID.randomUUID(), "Santander", 33, "#ff0000"));
        return banks;
    }

    // ============ Role ============
    public static List<Role> createRoles() {
        List<Role> roles = new ArrayList<>();
        roles.add(new Role(UUID.randomUUID(), "ROLE_USER"));
        roles.add(new Role(UUID.randomUUID(), "ROLE_ADMIN"));
        return roles;
    }

    // ============ User ============
    public static List<User> createUsersWithRoles(List<Role> existingRoles) {
        List<User> users = new ArrayList<>();
//        List<Role> existingRoles = createRoles();
        List<Role> userRole = new ArrayList<>();
        userRole.add(existingRoles.get(0));

        users.add(new User(UUID.randomUUID(), "Mary Red", "mary@example.com", "123456", userRole));
        users.add(new User(UUID.randomUUID(), "Jhon Blue", "jhon@example.com", "123456", userRole));
        users.add(new User(UUID.randomUUID(), "Ted Brown", "ted@example.com", "123456", existingRoles));

        return users;
    }

    // ============ Account ============
    public static List<Account> createFullAccounts(List<Bank> existingBanks, List<User> existingUsers) {
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account(UUID.randomUUID(), "My Account", "0001", "123456", new BigDecimal("5000.00"), new BigDecimal("2000.00"), new BigDecimal("500.00"), new BigDecimal("2000.00"), new BigDecimal("1000.00"), existingBanks.get(0), existingUsers.get(0)));
        accounts.add(new Account(UUID.randomUUID(), "My Account", "0001", "123456", new BigDecimal("5000.00"), new BigDecimal("2000.00"), new BigDecimal("500.00"), new BigDecimal("2000.00"), new BigDecimal("1000.00"), existingBanks.get(1), existingUsers.get(0)));
        accounts.add(new Account(UUID.randomUUID(), "My Account", "0001", "123456", new BigDecimal("5000.00"), new BigDecimal("2000.00"), new BigDecimal("500.00"), new BigDecimal("2000.00"), new BigDecimal("1000.00"), existingBanks.get(2), existingUsers.get(1)));
        return accounts;
    }

    // ============ CreditCard ============
    public static List<CreditCard> createCreditCards(List<Account> existingAccounts) {
        List<CreditCard> creditCards = new ArrayList<>();
        creditCards.add(new CreditCard(UUID.randomUUID(), "Test Credit Card", 1234, new BigDecimal("5000.00"), 10, 20, YearMonth.of(2030, 12), true, existingAccounts.get(0)));
        creditCards.add(new CreditCard(UUID.randomUUID(), "Test Credit Card", 1234, new BigDecimal("5000.00"), 10, 20, YearMonth.of(2030, 12), true, existingAccounts.get(1)));
        creditCards.add(new CreditCard(UUID.randomUUID(), "Test Credit Card", 1234, new BigDecimal("5000.00"), 10, 20, YearMonth.of(2030, 12), true, existingAccounts.get(02)));
        return creditCards;
    }

    // ============ CreditCardBill ============
    public static List<CreditCardBill> createCreditCardBills(List<CreditCard> existingCards) {
        List<CreditCardBill> bills = new ArrayList<>();
        bills.add(new CreditCardBill(UUID.randomUUID(), LocalDate.now().plusDays(10), LocalDate.now(), new BigDecimal("500.00"), null, new BigDecimal("0"), CreditCardBillStatus.OPEN, existingCards.get(0)));
        bills.add(new CreditCardBill(UUID.randomUUID(), LocalDate.now().minusDays(20), LocalDate.now().minusDays(30), new BigDecimal("500.00"),  LocalDate.now().minusDays(20), new BigDecimal("500.00"), CreditCardBillStatus.PAYED, existingCards.get(1)));
        bills.add(new CreditCardBill(UUID.randomUUID(), LocalDate.now().plusDays(10), LocalDate.now(), new BigDecimal("500.00"), null, new BigDecimal("0"), CreditCardBillStatus.OPEN, existingCards.get(1)));
        bills.add(new CreditCardBill(UUID.randomUUID(), LocalDate.now().plusDays(5), LocalDate.now().minusDays(5), new BigDecimal("500.00"), null, new BigDecimal("0"), CreditCardBillStatus.CLOSED, existingCards.get(2)));

        return bills;
    }

    // ============ Transaction ============
    public static List<Transaction> createTransactions(List<User> existingUsers, List<Account> existingAccounts, List<CreditCardBill> existingBills) {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(UUID.randomUUID(), "Description", new BigDecimal("100.00"), LocalDate.now(), false, LocalDate.now().plusDays(5), LocalDate.now().plusDays(5), "OUT", 1, TransactionType.EXPENSE, TransactionCategory.FOOD, TransactionPaymentMethod.DEBIT_CARD, existingUsers.get(0), null, null));
        transactions.add(new Transaction(UUID.randomUUID(), "Description", new BigDecimal("100.00"), LocalDate.now(), false, LocalDate.now().plusDays(10), LocalDate.now().plusDays(10), "OUT", 1, TransactionType.EXPENSE, TransactionCategory.ENTERTAINMENT, TransactionPaymentMethod.CREDIT_CARD, existingUsers.get(0), null, existingBills.get(0)));
        transactions.add(new Transaction(UUID.randomUUID(), "Description", new BigDecimal("100.00"), LocalDate.now(), false, LocalDate.now().plusDays(5), LocalDate.now().plusDays(5), "OUT", 1, TransactionType.EXPENSE, TransactionCategory.FOOD, TransactionPaymentMethod.CASH, existingUsers.get(1), existingAccounts.get(0), null));
        return transactions;
   }

    // ============ Installment ============
    public static List<Installment> createInstallments(List<Transaction> existingTransactions) {
        List<Installment> installments = new ArrayList<>();
        installments.add(new Installment(UUID.randomUUID(), "Test Installment", new BigDecimal("100.00"), LocalDate.now().plusDays(10), 1, LocalDate.now().plusDays(5), existingTransactions.get(1)));
        return installments;
    }

    // ============ Notification ============
    public static List<Notification> createNotifications(List<User> users) {
        List<Notification> notifications = new ArrayList<>();
        notifications.add(new Notification(UUID.randomUUID(),"Welcome", "Welcome to the application. We are glad having you here", LocalDateTime.now(), users.get(0)));
        notifications.add(new Notification(UUID.randomUUID(),"Welcome", "Welcome to the application. We are glad having you here", LocalDateTime.now(), users.get(1)));
        notifications.add(new Notification(UUID.randomUUID(),"Welcome", "Welcome to the application. We are glad having you here", LocalDateTime.now(), users.get(2)));
        return notifications;
    }
}
