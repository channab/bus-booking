package com.testapp.busbooking.exceptions;

public class BadRequestExpection extends RuntimeException {

	public BadRequestExpection(String message) {
		super(message);
	}
}
