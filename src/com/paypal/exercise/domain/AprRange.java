package com.paypal.exercise.domain;

import java.math.BigDecimal;

public enum AprRange {
	MIN(new BigDecimal("0.000001")), MAX(new BigDecimal("100"));

	private AprRange(BigDecimal amount) {
		this.aprLimit = amount;
	}

	public BigDecimal getAprLimit() {
		return aprLimit;
	}

	private BigDecimal aprLimit;
	
	@Override
	public String toString() {		
		return this.aprLimit.toString();				
	}
}