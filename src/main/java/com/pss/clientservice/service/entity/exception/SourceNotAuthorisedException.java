package com.pss.clientservice.service.entity.exception;

public class SourceNotAuthorisedException extends Exception {

	public SourceNotAuthorisedException() {
	}

	public SourceNotAuthorisedException(String message) {
		super(message);
	}

	public SourceNotAuthorisedException(String message, Throwable cause) {
		super(message, cause);
	}

	public SourceNotAuthorisedException(Throwable cause) {
		super(cause);
	}

	public SourceNotAuthorisedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
