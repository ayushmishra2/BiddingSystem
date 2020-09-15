package com.bidding.application.serviceImpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bidding.application.entity.Buyer;
import com.bidding.application.repository.BuyerRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private BuyerRepository buyerRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Buyer buyer = buyerRepo.findByUsername(username);
		return new User(buyer.getUsername(), buyer.getPassword(), new ArrayList<>());
	}

}
