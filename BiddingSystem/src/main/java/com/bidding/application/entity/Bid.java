package com.bidding.application.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
public class Bid {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer bidId;
	@Column
	private Integer itemCode;
	@Column
	private String username;
	@Column
	private LocalDateTime placedAt;
	@Column
	private Integer bidPrice;
	@Column
	private BidStatus bidStatus;

	public Bid(Integer itemCode, String username, LocalDateTime placedAt, Integer bidPrice, BidStatus bidStatus) {
		super();
		this.itemCode = itemCode;
		this.username = username;
		this.placedAt = placedAt;
		this.bidPrice = bidPrice;
		this.bidStatus = bidStatus;
	}

}
