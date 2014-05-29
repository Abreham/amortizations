package com.paypal.exercise.domain;

import java.math.BigDecimal;

public enum BorrowAmountRange {
	MIN(new BigDecimal("0.01")), MAX(new BigDecimal("1000000000000"));

	private BorrowAmountRange(BigDecimal amount) {
		this.limit = amount;
	}

	public BigDecimal getLimit() {
		return limit;
	}

	private BigDecimal limit;

	@Override
	public String toString() {		
		return this.limit.toString();				
	}
	
}
