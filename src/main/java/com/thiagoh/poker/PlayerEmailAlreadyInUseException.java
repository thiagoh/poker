package com.thiagoh.poker;

public class PlayerEmailAlreadyInUseException extends PortalException{

	public PlayerEmailAlreadyInUseException() {
		super();
	}

	public PlayerEmailAlreadyInUseException(String message, Throwable cause) {
		super(message, cause);
	}

	public PlayerEmailAlreadyInUseException(String message) {
		super(message);
	}

	public PlayerEmailAlreadyInUseException(Throwable cause) {
		super(cause);
	}

}
