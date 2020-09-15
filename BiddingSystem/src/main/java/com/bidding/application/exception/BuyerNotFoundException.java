package com.bidding.application.exception;

public class BuyerNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1598726186187237805L;

	public BuyerNotFoundException() {
		super("Buyer Not Found");
	}

}
