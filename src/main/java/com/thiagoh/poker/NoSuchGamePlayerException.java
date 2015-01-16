package com.thiagoh.poker;

public class NoSuchGamePlayerException extends PortalException{

	public NoSuchGamePlayerException() {
		super();
	}

	public NoSuchGamePlayerException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoSuchGamePlayerException(String message) {
		super(message);
	}

	public NoSuchGamePlayerException(Throwable cause) {
		super(cause);
	}

}
