package com.bidding.application.util;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

public class ResponseBuilder {

	private ResponseBuilder() {

	}

	public static <T> ResponseEntity<?> created(T data) {

		ApiResponse<T> apiResponse = new ApiResponse.Builder<T>().setResult("SUCCESS")
				.setStatus(HttpStatus.CREATED.getReasonPhrase()).setStatusCode(HttpStatus.CREATED.value()).setData(data)
				.setTimestamp(new Date()).build();
		return new ResponseEntity<ApiResponse<T>>(apiResponse, HttpStatus.CREATED);
	}

	public static <T> ResponseEntity<?> created(T data, MultiValueMap<String, String> headers) {

		ApiResponse<T> apiResponse = new ApiResponse.Builder<T>().setResult("SUCCESS")
				.setStatus(HttpStatus.CREATED.getReasonPhrase()).setStatusCode(HttpStatus.CREATED.value()).setData(data)
				.setTimestamp(new Date()).build();
		return new ResponseEntity<ApiResponse<T>>(apiResponse, headers, HttpStatus.CREATED);
	}

	public static <T> ResponseEntity<?> ok(T data) {

		ApiResponse<T> apiResponse = new ApiResponse.Builder<T>().setResult("SUCCESS")
				.setStatus(HttpStatus.OK.getReasonPhrase()).setStatusCode(HttpStatus.OK.value()).setData(data)
				.setTimestamp(new Date()).build();
		return new ResponseEntity<ApiResponse<T>>(apiResponse, HttpStatus.CREATED);
	}

	public static <T> ResponseEntity<?> ok(T data, MultiValueMap<String, String> headers) {

		ApiResponse<T> apiResponse = new ApiResponse.Builder<T>().setResult("SUCCESS")
				.setStatus(HttpStatus.OK.getReasonPhrase()).setStatusCode(HttpStatus.OK.value()).setData(data)
				.setTimestamp(new Date()).build();
		return new ResponseEntity<ApiResponse<T>>(apiResponse, headers, HttpStatus.CREATED);
	}

}