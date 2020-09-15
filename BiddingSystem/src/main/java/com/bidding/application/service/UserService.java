package com.bidding.application.service;

import java.util.Optional;

import com.bidding.application.dto.AuthRequest;
import com.bidding.application.dto.AuthResponse;
import com.bidding.application.entity.Buyer;

public interface UserService {

	public AuthResponse generateToken(AuthRequest authRequest) throws Exception;

	public Optional<Buyer> getBuyerDetails(String username);
}
