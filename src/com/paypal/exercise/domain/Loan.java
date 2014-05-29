package com.paypal.exercise.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class Loan implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Money amountBorrowed = Money.dollars(new BigDecimal(0)); 
	private BigDecimal apr = new BigDecimal(0);
	private int initialTermMonths = 0;

	public Loan() {

	}

	public Money getAmountBorrowed() {
		return amountBorrowed;
	}

	public void setAmountBorrowed(Money amountBorrowed) {
		this.amountBorrowed = amountBorrowed;
	}


	public BigDecimal getApr() {
		return apr;
	}

	public void setApr(BigDecimal apr) {
		this.apr = apr;
	}

	public int getInitialTermMonths() {
		return initialTermMonths;
	}

	public void setInitialTermMonths(int initialTermMonths) {
		this.initialTermMonths = initialTermMonths;
	}

}
