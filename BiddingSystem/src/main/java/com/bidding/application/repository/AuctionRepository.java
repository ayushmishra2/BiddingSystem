package com.bidding.application.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bidding.application.entity.Auction;

public interface AuctionRepository extends CrudRepository<Auction, Integer> {

	@Query(value = "select * from Auction where auction_status = ?1", nativeQuery = true)
	Page<Auction> findAuctionByAuctionStatus(String status, Pageable pageable);

	Auction findByAuctionCode(Integer auctionCode);

}
