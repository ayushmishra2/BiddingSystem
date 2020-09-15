package com.bidding.application;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bidding.application.entity.AccountType;
import com.bidding.application.entity.Auction;
import com.bidding.application.entity.AuctionStatus;
import com.bidding.application.entity.Buyer;
import com.bidding.application.repository.AuctionRepository;
import com.bidding.application.repository.BuyerRepository;

@SpringBootApplication
public class BiddingSystemApplication {

	@Autowired
	private BuyerRepository buyerRepo;

	@Autowired
	private AuctionRepository auctionRepo;

	public static void main(String[] args) {
		SpringApplication.run(BiddingSystemApplication.class, args);
	}

	@PostConstruct
	public void initBuyers() {
		List<Buyer> list = Stream
				.of(new Buyer("user1", "password", "user1@gmail.com", AccountType.BUYER),
						new Buyer("user2", "password", "user2@gmail.com", AccountType.BUYER))
				.collect(Collectors.toList());
		buyerRepo.saveAll(list);
	}

	@PostConstruct
	public void initAuction() {
		List<Auction> auctions = Stream.of(
				new Auction(1, "A", 1000, 250, 1000, AuctionStatus.RUNNING, LocalDateTime.now(),
						LocalDateTime.now().plusDays(6)),
				new Auction(2, "B", 3000, 250, 3000, AuctionStatus.RUNNING, LocalDateTime.now(),
						LocalDateTime.now().plusDays(6)),
				new Auction(3, "C", 2000, 250, 2000, AuctionStatus.RUNNING, LocalDateTime.now(),
						LocalDateTime.now().plusDays(6)),
				new Auction(4, "D", 4000, 250, 4000, AuctionStatus.RUNNING, LocalDateTime.now(),
						LocalDateTime.now().plusDays(6)),
				new Auction(5, "E", 5000, 250, 5000, AuctionStatus.RUNNING, LocalDateTime.now(),
						LocalDateTime.now().plusDays(6)))
				.collect(Collectors.toList());
		auctionRepo.saveAll(auctions);
	}

}
