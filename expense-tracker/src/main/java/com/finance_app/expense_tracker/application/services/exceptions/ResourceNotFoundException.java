package com.finance_app.expense_tracker.application.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException(String msg) {
		super(msg);
	}
}
