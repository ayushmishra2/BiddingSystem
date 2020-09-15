package com.bidding.application.exception;

public class AuctionNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 2931691753406405813L;

	public AuctionNotFoundException() {
		super("Auction Not Found");
	}

}
