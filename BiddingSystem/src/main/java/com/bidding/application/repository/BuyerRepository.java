package com.bidding.application.repository;

import org.springframework.data.repository.CrudRepository;

import com.bidding.application.entity.Buyer;

public interface BuyerRepository extends CrudRepository<Buyer, Integer> {

	Buyer findByUsername(String username);

}
