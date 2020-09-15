package com.bidding.application.service;

import java.util.List;
import java.util.Optional;

import com.bidding.application.dto.BidRequestDto;
import com.bidding.application.entity.Auction;
import com.bidding.application.entity.Bid;

public interface AuctionService {

	Optional<List<Auction>> fetchAuctionByStatus(String status, Integer pageRequest, Integer pageSize);

	Optional<Bid> placeBid(Integer auctionCode, BidRequestDto bidRequestDto);

	Optional<Auction> fetchAuctionByAuctionCode(Integer auctionCode);

	Optional<List<Bid>> fetchBidsByBuyer(String username);

}
