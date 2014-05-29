package com.paypal.exercise.utils;

import java.math.BigDecimal;

import com.paypal.exercise.domain.AprRange;
import com.paypal.exercise.domain.BorrowAmountRange;
import com.paypal.exercise.domain.Money;
import com.paypal.exercise.domain.TermRange;

public class LoanUtils {

	public static boolean isValidBorrowAmount(Money money) {
           if(money == null){
        	   return false;
           }
		 
		return ((BorrowAmountRange.MIN.getLimit().compareTo(money.getAmount()) == -1) && 
				(BorrowAmountRange.MAX.getLimit().compareTo(money.getAmount()) == 1));
	}

	public static boolean isValidAPRValue(BigDecimal rate) {
		 if(rate == null){
			 return false;
		 }
		
		return ((AprRange.MIN.getAprLimit().compareTo(rate) == -1) &&
				(AprRange.MAX.getAprLimit().compareTo(rate) == 1));
	}

	public static boolean isValidTerm(int years) {
		
		return ((TermRange.MIN.getTermLimit()<= years) && 
				(years <= TermRange.MAX.getTermLimit()));
	}
}
