package com.alumni.management.exception;




//We create this file to not write exception code every time
public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException(String message) {
		super(message);
	}
	

}
