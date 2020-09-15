package com.bidding.application.validator;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.bidding.application.entity.Auction;

@Component
public class AuctionValidator {

	public boolean validateBid(Auction auction) {
		if (auction.getEndDate().isBefore(LocalDateTime.now())
				|| auction.getAuctionStatus().toString().equals("OVER")) {
			return false;
		}
		return true;
	}
}
