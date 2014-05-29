package com.paypal.exercise.domain;

public enum TermRange {
	MIN(1), MAX(1000000);

	private TermRange(int amount) {
		this.termLimit = amount;
	}

	public int getTermLimit() {
		return termLimit;
	}

	private int termLimit;
	
	@Override
	public String toString() {		
		return Integer.toString(termLimit);				
	}
}