package com.bidding.application.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.bidding.application.dto.BidRequestDto;
import com.bidding.application.entity.Auction;
import com.bidding.application.entity.Bid;
import com.bidding.application.entity.BidStatus;
import com.bidding.application.exception.BidNotFoundException;
import com.bidding.application.repository.AuctionRepository;
import com.bidding.application.repository.BidRepository;
import com.bidding.application.service.AuctionService;
import com.bidding.application.validator.AuctionValidator;

@Service
public class AuctionServiceImpl implements AuctionService {

	@Autowired
	private AuctionRepository auctionRepo;

	@Autowired
	private BidRepository bidRepo;

	@Autowired
	private AuctionValidator auctionValidator;

	@Override
	public Optional<List<Auction>> fetchAuctionByStatus(String status, Integer pageRequest, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageRequest, pageSize);
		Page<Auction> auctions = auctionRepo.findAuctionByAuctionStatus(status, pageable);
		return Optional.of(auctions.toList());
	}

	@Override
	public Optional<Bid> placeBid(Integer auctionCode, BidRequestDto bidRequestDto) {
		Optional<Bid> bid = Optional.empty();
		Auction auction = fetchAuctionByAuctionCode(auctionCode).get();
		if (auction != null && auctionValidator.validateBid(auction)) {
			if (auction.getCurrBidPrice() <= bidRequestDto.getBidPrice()
					&& LocalDateTime.now().isBefore(auction.getEndDate())
					&& auction.getAuctionStatus().toString().equals("RUNNING")) {
				auction.setCurrBidPrice(bidRequestDto.getBidPrice() + auction.getStepRate());
				bid = Optional.of(new Bid(auctionCode, getUsernameFromSecurityContext(), LocalDateTime.now(),
						bidRequestDto.getBidPrice(), BidStatus.ACCEPTED));
				auctionRepo.save(auction);
			} else {
				bid = Optional.of(new Bid(auctionCode, getUsernameFromSecurityContext(), LocalDateTime.now(),
						bidRequestDto.getBidPrice(), BidStatus.REJECTED));
			}
			bidRepo.save(bid.get());
			// Buyer buyer = buyerRepo.findByUsername(getUsernameFromSecurityContext());
		}
		return bid;
	}

	@Override
	public Optional<Auction> fetchAuctionByAuctionCode(Integer auctionCode) {
//		Optional<Auction> auction = Optional.of(auctionRepo.findByAuctionCode(auctionCode));
//		return auction.orElseThrow(() -> new AuctionNotFoundException());
		return Optional.of(auctionRepo.findByAuctionCode(auctionCode));

	}

	private String getUsernameFromSecurityContext() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userDetails.getUsername();
		return username;
	}

	@Override
	public Optional<List<Bid>> fetchBidsByBuyer(String username) {
		// TODO Auto-generated method stub
		Optional<List<Bid>> bidList = Optional.of(bidRepo.findByUsername(username));
		if (bidList.get().size() == 0) {
			throw new BidNotFoundException();
		}
		return bidList;
	}

}
