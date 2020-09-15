package com.bidding.application.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.bidding.application.dto.AuthRequest;
import com.bidding.application.dto.AuthResponse;
import com.bidding.application.entity.Buyer;
import com.bidding.application.repository.BuyerRepository;
import com.bidding.application.service.UserService;
import com.bidding.application.util.JwtUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private BuyerRepository buyerRepo;

	@Override
	public AuthResponse generateToken(AuthRequest authRequest) throws Exception {
		// TODO Auto-generated method stub
		try {
			authManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		} catch (Exception ex) {
			throw new Exception("Invalid email/password");
		}
		return new AuthResponse(jwtUtil.generateToken(authRequest.getUsername()));
	}

	@Override
	public Optional<Buyer> getBuyerDetails(String username) {
		// TODO Auto-generated method stub
		return Optional.of(buyerRepo.findByUsername(username));
	}

}
