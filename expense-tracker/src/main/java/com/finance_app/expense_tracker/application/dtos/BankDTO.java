package com.finance_app.expense_tracker.application.dtos;

import com.finance_app.expense_tracker.core.entities.Bank;

import java.time.Instant;
import java.util.UUID;

public class BankDTO {
    private UUID id;
    private String name;
    private String number;
    private String colorLabel;

    public BankDTO() {
    }

    public BankDTO(Bank bank) {
        this.id = bank.getId();
        this.name = bank.getName();
        this.number = String.format("%03d", bank.getNumber());
        this.colorLabel = bank.getColorLabel();
    }

    public BankDTO(UUID id, String name, String number, String colorLabel) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.colorLabel = colorLabel;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getColorLabel() {
        return colorLabel;
    }

    public void setColorLabel(String colorLabel) {
        this.colorLabel = colorLabel;
    }
}
