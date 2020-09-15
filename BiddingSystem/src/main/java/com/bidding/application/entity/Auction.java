package com.bidding.application.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Auction {

	@Id
	private Integer auctionCode;
	@Column
	private String auctionName;
	@Column
	private Integer minBidPrice;
	@Column
	private Integer stepRate;
	@Column
	private Integer currBidPrice;
	@Column
	@Enumerated(EnumType.STRING)
	private AuctionStatus auctionStatus;
	@Column
	private LocalDateTime createdAt;
	@Column
	private LocalDateTime endDate;

	public Auction(String auctionName, Integer minBidPrice, Integer stepRate, Integer currBidPrice,
			AuctionStatus auctionStatus, LocalDateTime createdAt, LocalDateTime endDate) {
		super();
		this.auctionName = auctionName;
		this.minBidPrice = minBidPrice;
		this.stepRate = stepRate;
		this.currBidPrice = currBidPrice;
		this.auctionStatus = auctionStatus;
		this.createdAt = createdAt;
		this.endDate = endDate;
	}

}

// new Auction(1,"A",1000,250,1000,AuctionStatus.RUNNING,LocalDateTime.NOW,LocalDateTime.plus()
