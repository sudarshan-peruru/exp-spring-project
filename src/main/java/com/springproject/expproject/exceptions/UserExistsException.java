package com.springproject.expproject.exceptions;

public class UserExistsException extends Exception{
	
	private static final long serialVersionUID = 2689643631994668918L;

	public UserExistsException(String message) {
		super(message);
	}

}
