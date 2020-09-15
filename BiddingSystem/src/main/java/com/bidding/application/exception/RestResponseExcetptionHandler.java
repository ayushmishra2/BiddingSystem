package com.bidding.application.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseExcetptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { AuctionNotFoundException.class, BuyerNotFoundException.class,
			BidNotFoundException.class })
	public ResponseEntity<?> handleAuctionNotFoundException(RuntimeException ex, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", ex.getMessage());
		body.put("status", HttpStatus.NOT_FOUND.value());

		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = UserNotLoggedInException.class)
	public ResponseEntity<?> handleUserNotLoggedInException(RuntimeException ex, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", ex.getMessage());

		return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
	}
}
