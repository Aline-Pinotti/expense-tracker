package com.finance_app.expense_tracker.application.dtos;

import com.finance_app.expense_tracker.core.entities.Transaction;
import com.finance_app.expense_tracker.core.enums.TransactionCategory;
import com.finance_app.expense_tracker.core.enums.TransactionPaymentMethod;
import com.finance_app.expense_tracker.core.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class TransactionDTO {

    private UUID id;
    private String description;
    private BigDecimal amount;
    private LocalDate date;

    private Boolean isFixed; //Signatures or water, energy etc - with no fixed number of installments
    private LocalDate dueDate;
    private LocalDate paidDate;
    private String inOut;
    private Integer numberOfInstallments; ;//(so batch will know if it should generate next installment)

    // private List<Installment> installments = new ArrayList<>();

    private TransactionType type;
    private TransactionCategory mainCategory;

    // private Set<TransactionCategory> subCategories = new HashSet<>();

    private TransactionPaymentMethod paymentMethod;

    private UserDTO user;
    private UUID accountId; // ou null se for cartão
    private UUID creditCardBillId; // If credit card

    public TransactionDTO() {
    }

    public TransactionDTO(Transaction entity) {
        this.id = entity.getId();
        this.description = entity.getDescription();
        this.amount = entity.getAmount();
        this.date = entity.getDate();
        this.isFixed = entity.getFixed();
        this.dueDate = entity.getDueDate();
        this.paidDate = entity.getPaidDate();
        this.inOut = entity.getInOut();
        this.numberOfInstallments = entity.getNumberOfInstallments();
        this.type = entity.getType();
        this.mainCategory = entity.getMainCategory();
        this.paymentMethod = entity.getPaymentMethod();
        this.user = new UserDTO(entity.getUser());
        if (entity.getAccount() != null) this.accountId = entity.getAccount().getId();
        if (entity.getCreditCardBill() != null) this.creditCardBillId = entity.getCreditCardBill().getId();
    }

    public TransactionDTO(UUID id, String description, BigDecimal amount, LocalDate date, Boolean isFixed, LocalDate dueDate, LocalDate paidDate, String inOut, Integer numberOfInstallments, TransactionType type, TransactionCategory mainCategory, TransactionPaymentMethod paymentMethod, UserDTO user, UUID accountId, UUID creditCardBillId) {
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
        this.user = user;
        this.accountId = accountId;
        this.creditCardBillId = creditCardBillId;
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

    public Boolean isFixed() {
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

    public TransactionPaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(TransactionPaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public UUID getCreditCardBillId() {
        return creditCardBillId;
    }

    public void setCreditCardBillId(UUID creditCardBillId) {
        this.creditCardBillId = creditCardBillId;
    }
}
