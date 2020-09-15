package com.bidding.application.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bidding.application.dto.BidRequestDto;
import com.bidding.application.entity.Auction;
import com.bidding.application.entity.Bid;
import com.bidding.application.exception.AuctionNotFoundException;
import com.bidding.application.exception.BidNotFoundException;
import com.bidding.application.repository.AuctionRepository;
import com.bidding.application.service.AuctionService;

@RestController
@RequestMapping("/auction")
public class AuctionController {

	@Autowired
	private AuctionService auctionService;

	@Autowired
	AuctionRepository auctionRepo;

	@GetMapping("/{pageRequest}/{pageSize}")
	public ResponseEntity<?> fetchAuctionByStatus(@RequestParam("status") String status,
			@PathVariable("pageRequest") Integer pageRequest, @PathVariable("pageSize") Integer pageSize) {
		try {
			Optional<List<Auction>> auctions = auctionService.fetchAuctionByStatus(status.toUpperCase(), pageRequest,
					pageSize);
			return new ResponseEntity<>(auctions.get(), HttpStatus.OK);
		} catch (Exception ex) {
			throw new AuctionNotFoundException();
		}
	}

	@GetMapping("/all")
	public ResponseEntity<?> Status() {
		return new ResponseEntity<>(auctionRepo.findAll(), HttpStatus.OK);
	}

	@PostMapping("/{auctionCode}/bid")
	public ResponseEntity<?> placeBid(@PathVariable("auctionCode") Integer auctionCode,
			@RequestBody BidRequestDto bidRequestDto) {
		try {
			Optional<Bid> bid = auctionService.placeBid(auctionCode, bidRequestDto);
			if (bid.get().getBidStatus().toString().equals("ACCEPTED"))
				return new ResponseEntity<>(bid, HttpStatus.CREATED);
			else
				return new ResponseEntity<>(bid, HttpStatus.NOT_ACCEPTABLE);
		} catch (Exception ex) {
			throw new AuctionNotFoundException();
		}
		// throw new AuctionNotFoundException("as");
	}

	@GetMapping("/{username}/bids")
	public ResponseEntity<?> fetchBidsByBuyer(@PathVariable("username") String username) {
		try {
			Optional<List<Bid>> bidList = auctionService.fetchBidsByBuyer(username);
			return new ResponseEntity<>(bidList, HttpStatus.OK);
		} catch (Exception ex) {
			throw new BidNotFoundException();
		}
	}
}
