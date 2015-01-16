package com.thiagoh.poker;

public class NoSuchPlayerException extends PortalException{

	public NoSuchPlayerException() {
		super();
	}

	public NoSuchPlayerException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoSuchPlayerException(String message) {
		super(message);
	}

	public NoSuchPlayerException(Throwable cause) {
		super(cause);
	}

}
