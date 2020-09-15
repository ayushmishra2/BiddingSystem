package com.bidding.application.exception;

public class BidNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -6991199918600649358L;

	public BidNotFoundException() {
		super("Bid Not Found");
	}

}
