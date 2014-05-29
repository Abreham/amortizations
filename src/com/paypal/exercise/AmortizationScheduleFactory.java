package com.paypal.exercise;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import com.paypal.exercise.domain.AmortizationSchedule;
import com.paypal.exercise.domain.Loan;
import com.paypal.exercise.domain.Money;
import com.paypal.exercise.exception.AmortizationException;
import com.paypal.exercise.utils.LoanUtils;
/** 
 * @author Abreham
 * 
 * payment schedules can be arranged on monthly or quarterly or annually
 * for this exercise we assume to use a monthly base payment
 */
public class AmortizationScheduleFactory {

	public AmortizationSchedule getInstance(Money principal,
			BigDecimal interestRate, int years) {

		if ((LoanUtils.isValidBorrowAmount(principal) == false)
				|| (LoanUtils.isValidAPRValue(interestRate) == false)
				|| (LoanUtils.isValidTerm(years) == false)) {
			throw new AmortizationException("Invalid variables for the loan ....");
		}

		Loan loan = new Loan();
		loan.setAmountBorrowed(principal);
		loan.setApr(interestRate);
		loan.setInitialTermMonths(years * 12);

		AmortizationSchedule schedule = new AmortizationSchedule();
		schedule.setLoan(loan);
		schedule.setMonthlyInterest(interestRate.divide(AmortizationSchedule.MONTHLY_INTEREST_DIVISOR,6,RoundingMode.HALF_UP));

		Money monthlyPayment = calculateMonthlyPayment(schedule);
		schedule.setMonthlyPayment(monthlyPayment);

		if (monthlyPayment.getAmount().compareTo(principal.getAmount()) == 1) {
			throw new AmortizationException();
		}
		return schedule;
	}

	private Money calculateMonthlyPayment(AmortizationSchedule schedule) {

		BigDecimal monthlyInterestRate = getMonthlyInterest(schedule);
		BigDecimal divider = BigDecimal.ONE.add(monthlyInterestRate);
		BigDecimal tmp = BigDecimal.ONE.divide(divider,5,RoundingMode.HALF_UP);
		
		
		tmp = tmp.pow(schedule.getLoan().getInitialTermMonths(),MathContext.UNLIMITED);
		tmp = BigDecimal.ONE.subtract(tmp);
		tmp = BigDecimal.ONE.divide(tmp,5,RoundingMode.HALF_UP);
		
		BigDecimal rc = schedule.getLoan().getAmountBorrowed().getAmount()
				.multiply(monthlyInterestRate).multiply(tmp);
		return Money.dollars(rc);
	}
	
	public BigDecimal getMonthlyInterest (AmortizationSchedule schedule){
		BigDecimal monthlyInterest = schedule.getLoan().getApr()
				.divide(AmortizationSchedule.MONTHLY_INTEREST_DIVISOR,3,RoundingMode.HALF_UP);
		
		return monthlyInterest;
	}

}
