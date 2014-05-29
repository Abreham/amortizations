package com.paypal.exercise.exception;

public class AmortizationException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public AmortizationException() {
		super();
	}

	public AmortizationException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public AmortizationException(String message) {
		super(message);
	}
}
