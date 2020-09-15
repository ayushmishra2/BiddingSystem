package com.bidding.application.exception;

public class UserNotLoggedInException extends RuntimeException {

	private static final long serialVersionUID = -2979274025291047508L;

	public UserNotLoggedInException() {
		super("User Not Logged In");
	}

}
