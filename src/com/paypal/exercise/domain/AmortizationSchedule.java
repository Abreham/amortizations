package com.paypal.exercise.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class AmortizationSchedule implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final BigDecimal MONTHLY_INTEREST_DIVISOR = new BigDecimal("1200");
	private BigDecimal monthlyInterest = BigDecimal.ZERO;
	private Money monthlyPayment = Money.dollars(BigDecimal.ZERO); // in cents

	private Loan loan;

	public AmortizationSchedule() {

	}

	public BigDecimal getMonthlyInterest() {
		return monthlyInterest;
	}

	public void setMonthlyInterest(BigDecimal monthlyInterest) {
		this.monthlyInterest = monthlyInterest;
	}

	public Money getMonthlyPayment() {
		return monthlyPayment;
	}

	public void setMonthlyPayment(Money monthlyPayment) {
		this.monthlyPayment = monthlyPayment;
	}

	public Loan getLoan() {
		return loan;
	}

	public void setLoan(Loan loan) {
		this.loan = loan;
	}
}
