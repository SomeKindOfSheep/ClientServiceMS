package com.pss.clientservice.service.exception;

public class ExceptionResponse {

    private String responseType;
    private String message;
    private String code;

    public ExceptionResponse(String responseType, String message, String code) {
        this.responseType = responseType;
        this.message = message;
        this.code = code;
    }

    public ExceptionResponse() {

    }


    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

