package com.finance_app.expense_tracker.core.entities;

import com.finance_app.expense_tracker.core.enums.TransactionCategory;
import com.finance_app.expense_tracker.core.enums.TransactionPaymentMethod;
import com.finance_app.expense_tracker.core.enums.TransactionType;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

public class Transaction {

    //TODO auditory class for the imported transactions when suffer updates

    private UUID id;
    private String description;
    private BigDecimal amount;
    private LocalDate date;

    private Boolean isFixed; //Signatures or water, energy etc - with no fixed number of installments
    private LocalDate dueDate;
    private LocalDate paidDate;
    private String inOut;
    private Integer numberOfInstallments; ;//(so batch will know if it should generate next installment)

    private List<Installment> installments = new ArrayList<>();

    private TransactionType type;
    private TransactionCategory mainCategory;

    private Set<TransactionCategory> subCategories = new HashSet<>();

    private TransactionPaymentMethod paymentMethod;

    private Instant createdAt;
    private Instant updatedAt;

    private User user;
    private Account account; // ou null se for cartão
    //private CreditCard creditCard; // quando for compra no cartão, vai estar vinculada através da CreditCardBill
    private CreditCardBill billing; // If credit card

    public Transaction() {
    }

    public Transaction(UUID id, String description, BigDecimal amount, LocalDate date, Boolean isFixed, LocalDate dueDate, LocalDate paidDate, String inOut, Integer numberOfInstallments, TransactionType type, TransactionCategory mainCategory, TransactionPaymentMethod paymentMethod, Instant createdAt, Instant updatedAt, User user, Account account, CreditCardBill billing) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.isFixed = isFixed;
        this.dueDate = dueDate;
        this.paidDate = paidDate;
        this.inOut = inOut;
        this.numberOfInstallments = numberOfInstallments;
        this.type = type;
        this.mainCategory = mainCategory;
        this.paymentMethod = paymentMethod;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.user = user;
        this.account = account;
        this.billing = billing;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Boolean getFixed() {
        return isFixed;
    }

    public void setFixed(Boolean fixed) {
        isFixed = fixed;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(LocalDate paidDate) {
        this.paidDate = paidDate;
    }

    public String getInOut() {
        return inOut;
    }

    public void setInOut(String inOut) {
        this.inOut = inOut;
    }

    public Integer getNumberOfInstallments() {
        return numberOfInstallments;
    }

    public void setNumberOfInstallments(Integer numberOfInstallments) {
        this.numberOfInstallments = numberOfInstallments;
    }

    public List<Installment> getInstallments() {
        return installments;
    }

    public void setInstallments(List<Installment> installments) {
        this.installments = installments;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public TransactionCategory getMainCategory() {
        return mainCategory;
    }

    public void setMainCategory(TransactionCategory mainCategory) {
        this.mainCategory = mainCategory;
    }

    public Set<TransactionCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(Set<TransactionCategory> subCategories) {
        this.subCategories = subCategories;
    }

    public TransactionPaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(TransactionPaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public CreditCardBill getBilling() {
        return billing;
    }

    public void setBilling(CreditCardBill billing) {
        this.billing = billing;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                ", isFixed=" + isFixed +
                ", dueDate=" + dueDate +
                ", paidDate=" + paidDate +
                ", inOut='" + inOut + '\'' +
                ", numberOfInstallments=" + numberOfInstallments +
                ", type=" + type +
                ", mainCategory=" + mainCategory +
                ", paymentMethod=" + paymentMethod +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", user=" + user +
                ", account=" + account +
                ", billing=" + billing +
                '}';
    }
}
