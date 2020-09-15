package com.bidding.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bidding.application.dto.AuthRequest;
import com.bidding.application.dto.AuthResponse;
import com.bidding.application.exception.UserNotLoggedInException;
import com.bidding.application.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/authenticate")
	public ResponseEntity<AuthResponse> generateToken(@RequestBody AuthRequest authRequest) throws Exception {

		try {
			return new ResponseEntity<AuthResponse>(userService.generateToken(authRequest), HttpStatus.OK);
		} catch (Exception ex) {
			throw new UserNotLoggedInException();
		}
	}

}
