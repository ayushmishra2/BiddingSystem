package com.bidding.application.entity;

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
public class Buyer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column
	private String username;
	@Column
	private String password;
	@Column
	private String email;
	@Column
	private AccountType accountType;

	public Buyer(String username, String password, String email, AccountType accountType) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.accountType = accountType;
	}

}
