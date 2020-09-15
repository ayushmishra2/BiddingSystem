package com.bidding.application.util;

import java.util.Date;

import lombok.Data;

@Data
public class ApiResponse<T> {

	private String result;
	private String status;
	private Integer statusCode;
	private T data;
	private Date timestamp;

	public ApiResponse(Builder<T> builder) {
		this.result = builder.result;
		this.status = builder.status;
		this.statusCode = builder.statusCode;
		this.timestamp = builder.timestamp;
		this.data = builder.data;
	}

	public static class Builder<T> {

		private String result;
		private String status;
		private Integer statusCode;
		private T data;
		private Date timestamp;

		public Builder<T> setResult(String result) {
			this.result = result;
			return this;
		}

		public Builder<T> setStatus(String status) {
			this.status = status;
			return this;
		}

		public Builder<T> setStatusCode(Integer statusCode) {
			this.statusCode = statusCode;
			return this;
		}

		public Builder<T> setData(T data) {
			this.data = data;
			return this;
		}

		public Builder<T> setTimestamp(Date timestamp) {
			this.timestamp = timestamp;
			return this;
		}

		public ApiResponse<T> build() {
			return new ApiResponse<T>(this);
		}
	}
}