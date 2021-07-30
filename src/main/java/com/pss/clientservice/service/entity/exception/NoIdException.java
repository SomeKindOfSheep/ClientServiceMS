package com.pss.clientservice.service.entity.exception;

public class NoIdException extends Exception {

	private static final long serialVersionUID = 1L;

	public NoIdException() {
	}

	public NoIdException(String message) {
		super(message);
	}

	public NoIdException(Throwable cause) {
		super(cause);
	}

	public NoIdException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoIdException(String message, Throwable cause, boolean enableSuppression,
                         boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
