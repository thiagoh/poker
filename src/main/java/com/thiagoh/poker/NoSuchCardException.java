package com.thiagoh.poker;

public class NoSuchCardException extends PortalException{

	public NoSuchCardException() {
		super();
	}

	public NoSuchCardException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoSuchCardException(String message) {
		super(message);
	}

	public NoSuchCardException(Throwable cause) {
		super(cause);
	}

}
