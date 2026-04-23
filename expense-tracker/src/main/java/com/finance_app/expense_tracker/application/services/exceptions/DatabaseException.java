package com.finance_app.expense_tracker.application.services.exceptions;

public class DatabaseException extends RuntimeException {

	public DatabaseException(String msg) {
		super(msg);
	}
}
