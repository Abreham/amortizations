package com.paypal.exercise;

import java.io.IOException;
import java.math.BigDecimal;

import com.paypal.exercise.domain.AmortizationSchedule;
import com.paypal.exercise.domain.AprRange;
import com.paypal.exercise.domain.BorrowAmountRange;
import com.paypal.exercise.domain.Money;
import com.paypal.exercise.domain.TermRange;
import com.paypal.exercise.utils.LoanUtils;

public class AmortizationScheduleTest {
 private static AmortizationScheduleFactory factory = new AmortizationScheduleFactory();
	
	public static void main(String[] args) {
        
		String[] userPrompts = {
				"Please enter the amount you would like to borrow: ",
				"Please enter the annual percentage rate used to repay the loan: ",
				"Please enter the term, in years, over which the loan is repaid: " };

		String line = "";
		BigDecimal amount = BigDecimal.ZERO;
		BigDecimal apr =BigDecimal.ZERO;
		int years = 0;

		for (int i = 0; i < userPrompts.length;) {
			String userPrompt = userPrompts[i];
			try {
				line = MyConsole.readLine(userPrompt);
			} catch (IOException e) {
				MyConsole.print("An IOException was encountered. Terminating program.\n");
				return;
			}

			boolean isValidValue = true;
			try {
				switch (i) {
				case 0:
					amount = new BigDecimal(line);
					//LoanUtils.isValidAPRValue(interestRate)
					if (LoanUtils.isValidBorrowAmount(Money.dollars(amount)) == false) {
						isValidValue = false;
						MyConsole.print("Please enter a positive value between "
								+ BorrowAmountRange.MIN.getLimit() + " and " + BorrowAmountRange.MAX.getLimit()+ ". ");
					}
					break;
				case 1:
					apr = new BigDecimal(line);
					if (LoanUtils.isValidAPRValue(apr) == false) {
						isValidValue = false;
						MyConsole.print("Please enter a positive value between "
								+ AprRange.MIN + " and " + AprRange.MAX + ". ");
					}
					break;
				case 2:
					years = Integer.parseInt(line);
					if (LoanUtils.isValidTerm(years) == false) {
						isValidValue = false;
						MyConsole.print("Please enter a positive integer value between "
								+ TermRange.MIN + " and " + TermRange.MAX + ". ");
					}
					break;
				}
			} catch (NumberFormatException e) {
				isValidValue = false;
			}
			if (isValidValue) {
				i++;
			} else {
				MyConsole.print("An invalid value was entered.\n");
			}
		}

		try {
			
			AmortizationSchedule schedule = factory.getInstance(Money.dollars(amount), apr, years);
            MyConsole.outputAmortizationSchedule(schedule);
            
		} catch (IllegalArgumentException e) {
			MyConsole.print("Unable to process the values entered. Terminating program.\n");
		}
	}
}
