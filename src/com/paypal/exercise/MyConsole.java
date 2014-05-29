package com.paypal.exercise;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.IllegalFormatException;

import com.paypal.exercise.domain.AmortizationSchedule;
import com.paypal.exercise.domain.Money;

public class MyConsole {
	
	private static Console console = System.console();
	
	public static void printf(String formatString, Object... args) {

		try {
			if (console != null) {
				console.printf(formatString, args);
			} else {
				System.out.print(String.format(formatString, args));
			}
		} catch (IllegalFormatException e) {
			System.err.print("Error printing...\n");
		}
	}

	public static void print(String s) {
		printf("%s", s);
	}

	public static String readLine(String userPrompt) throws IOException {
		String line = "";

		if (console != null) {
			line = console.readLine(userPrompt);
		} else {
			// print("console is null\n");
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(System.in));

			print(userPrompt);
			line = bufferedReader.readLine();
		}
		line.trim();
		return line;
	}
	
	public static void outputAmortizationSchedule(AmortizationSchedule schedule) {

		String formatString = "%1$-20s %2$-20s %3$-20s %4$s, %5$s, %6$s\n";
		printf(formatString, "PaymentNumber", "PaymentAmount",
				"PaymentInterest", "CurrentBalance", "TotalPayments",
				"TotalInterestPaid");

		Money balance = schedule.getLoan().getAmountBorrowed();
		int paymentNumber = 0;
		Money totalPayments = Money.dollars(BigDecimal.ZERO);
		Money totalInterestPaid = Money.dollars(BigDecimal.ZERO);

		formatString = "%1$-20d%2$-20s%3$-20s%4$-20s%5$-20s%6$s\n";
		printf(formatString, paymentNumber++, Money.dollars(BigDecimal.ZERO), Money.dollars(BigDecimal.ZERO),
				balance,totalPayments,totalInterestPaid);
				

		final int maxNumberOfPayments = schedule.getLoan().getInitialTermMonths() + 1;
		while ((balance.getAmount().signum() > 0) && (paymentNumber <= maxNumberOfPayments)) {
			Money curMonthlyInterest = Money.dollars(balance.getAmount().multiply(schedule.getMonthlyInterest()));
			Money curPayoff = Money.add(balance,curMonthlyInterest);
			Money curMonthlyPayment = Money.dollars(schedule.getMonthlyPayment().getAmount().min(curPayoff.getAmount()));
			if ((paymentNumber == maxNumberOfPayments)
					&& ((curMonthlyPayment.getAmount().compareTo(BigDecimal.ZERO) == 0)
					|| (curMonthlyPayment == curMonthlyInterest))) {
				          curMonthlyPayment = curPayoff;
			}
			
			Money curMonthlyPrincipalPaid = Money.sub(curMonthlyPayment,curMonthlyInterest);
			Money curBalance = Money.sub(balance,curMonthlyPrincipalPaid);
			totalPayments =  Money.add(totalPayments,curMonthlyPayment);
			totalInterestPaid = Money.add(totalInterestPaid,curMonthlyInterest);
			
			// output is in dollars
			printf(formatString, paymentNumber++,curMonthlyPayment,curMonthlyInterest,
					curBalance,totalPayments,totalInterestPaid);
			
			balance = curBalance;
		}
	}
}
