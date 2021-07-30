package com.pss.clientservice.service.entity.exception;

public class PlatformSslHandshakeException extends RuntimeException {
	private static final long serialVersionUID = 5337316118486360320L;

	public PlatformSslHandshakeException(String s) {
        super(s);
    }
}
