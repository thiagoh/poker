package com.thiagoh.poker;

public class NoSuchGameException extends PortalException{

	public NoSuchGameException() {
		super();
	}

	public NoSuchGameException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoSuchGameException(String message) {
		super(message);
	}

	public NoSuchGameException(Throwable cause) {
		super(cause);
	}

}
