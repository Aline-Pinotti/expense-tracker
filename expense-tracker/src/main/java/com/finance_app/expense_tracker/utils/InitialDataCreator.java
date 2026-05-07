package com.finance_app.expense_tracker.utils;

import com.finance_app.expense_tracker.application.services.BankService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class InitialDataCreator { //notate w/ order

    // Bank
    private BankService service;
    public void insert() {
        // TODO: intantiation for ObjectFactory

    }

}
