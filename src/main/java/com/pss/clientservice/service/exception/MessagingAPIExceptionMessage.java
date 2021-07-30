package com.pss.clientservice.service.exception;


import org.springframework.http.HttpStatus;

public enum MessagingAPIExceptionMessage {

    NO_ID_400("0001", HttpStatus.NOT_FOUND),
	
	DIGITAL_ID_PRINCIPAL_NOT_FOUND("3001", HttpStatus.UNAUTHORIZED),
	SYSTEM_PRINCIPAL_NOT_FOUND("0001", HttpStatus.UNAUTHORIZED),
	
	SSL_NOT_AUTHORISED("0005", HttpStatus.UNAUTHORIZED),

    BAD_MESSAGE_400("0996", HttpStatus.BAD_REQUEST),
    BAD_MESSAGE_404("0997", HttpStatus.NOT_FOUND),
    BAD_MESSAGE_500("0998", HttpStatus.INTERNAL_SERVER_ERROR),
    
    BAD_REQUEST_INBOX_MESSAGE_400("0002", HttpStatus.BAD_REQUEST),
    BAD_REQUEST_INBOX_MESSAGE_MARSHAL("0003", HttpStatus.BAD_REQUEST),

    UNSUPPORTED_OPERATION("0004", HttpStatus.NOT_FOUND),

    LOG_PROCESSING_EXCEPTION("0995", HttpStatus.INTERNAL_SERVER_ERROR),
    CATCH_ALL("0999", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String code;
    private HttpStatus httpStatus;

    MessagingAPIExceptionMessage(String code, HttpStatus httpStatus) {
        this.code = code;
        this.httpStatus = httpStatus;
    }


    public String getCode() {
        return code;
    }
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getResponseCode() {
        return String.format("%d.%s", httpStatus.value(), code);
    }

    @Override
    public String toString() {
        return String.format("%s - %s", getResponseCode(), httpStatus.getReasonPhrase());
    }



}
