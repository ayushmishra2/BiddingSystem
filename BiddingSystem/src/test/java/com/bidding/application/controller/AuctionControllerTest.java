package com.bidding.application.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.bidding.application.entity.Auction;
import com.bidding.application.entity.AuctionStatus;
import com.bidding.application.service.AuctionService;

@RunWith(SpringRunner.class)
@WebMvcTest(AuctionController.class)
public class AuctionControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AuctionService auctionService;

	public void testFetchAuctionByStatus() throws Exception {

		when(auctionService.fetchAuctionByStatus("running", 0, 5)).thenReturn(getAuctionList());
		this.mockMvc.perform(get("/auction/0/5?status=running")).andExpect(status().isOk());
	}

	private Optional<List<Auction>> getAuctionList() {
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
		return Optional.of(auctions);
	}

}
