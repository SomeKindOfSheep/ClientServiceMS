package com.pss.clientservice.service.exception;

public class MessagingAPIException extends RuntimeException {

    private static final long serialVersionUID = 3411044117380838460L;
    private final String code;
    private final MessagingAPIExceptionMessage exceptionMessage;

    public MessagingAPIException(String exceptionCode, String message, Throwable cause, MessagingAPIExceptionMessage exceptionMessage) {
        super(message, cause);
        code = exceptionCode;
        this.exceptionMessage = exceptionMessage;
    }

    public MessagingAPIException(String exceptionCode, String message, MessagingAPIExceptionMessage exceptionMessage) {
        super(message);
        code = exceptionCode;
        this.exceptionMessage = exceptionMessage;
    }
    
    public MessagingAPIExceptionMessage getMessagingAPIExceptionMessage() {
        return exceptionMessage;
    }

    public String getCode() {
        return code;
    }

    public static MessagingAPIException throwExceptionChain(MessagingAPIExceptionMessage exceptionMessage, MessagingAPII18nMessageResolver messageResolver, Exception e) {
        String errorCode = exceptionMessage.getCode();
        return new MessagingAPIException(errorCode, messageResolver.getMessage(errorCode), e, exceptionMessage);
    }

    public static MessagingAPIException throwException(MessagingAPIExceptionMessage geoLocatorExceptionMessage, MessagingAPII18nMessageResolver messageResolver) {
        String errorCode = geoLocatorExceptionMessage.getCode();
        return new MessagingAPIException(errorCode, messageResolver.getMessage(errorCode), geoLocatorExceptionMessage);
    }
}
