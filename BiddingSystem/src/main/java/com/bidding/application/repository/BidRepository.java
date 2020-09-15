package com.bidding.application.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bidding.application.entity.Bid;

public interface BidRepository extends CrudRepository<Bid, Integer> {

	List<Bid> findByUsername(String username);

}
