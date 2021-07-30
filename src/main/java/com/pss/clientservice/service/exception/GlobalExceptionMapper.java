package com.pss.clientservice.service.exception;

import com.pss.clientservice.service.entity.Constants;
import com.pss.clientservice.service.entity.exception.NoIdException;
import com.pss.clientservice.service.entity.exception.PlatformSslHandshakeException;
import com.pss.clientservice.service.entity.exception.ResourceNotFoundException;
import com.pss.clientservice.service.entity.exception.SourceNotAuthorisedException;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class GlobalExceptionMapper extends ResponseEntityExceptionHandler {

    @Autowired
    ErrorLoggingService errorLoggingService;


    @Autowired
    protected MessagingAPII18nMessageResolver messageResolver;

    @ExceptionHandler({NotImplementedException.class})
    public ResponseEntity<ExceptionResponse> exception(NotImplementedException e) {
        return errorLoggingService.logExceptionAndConstructErrorResponse(e, MessagingAPIExceptionMessage.UNSUPPORTED_OPERATION);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ExceptionResponse> exception(Exception e) {
        return errorLoggingService.logExceptionAndConstructErrorResponse(e, MessagingAPIExceptionMessage.CATCH_ALL);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ExceptionResponse> exception(ResourceNotFoundException e) {
        return errorLoggingService.logExceptionAndConstructErrorResponse(e, MessagingAPIExceptionMessage.BAD_MESSAGE_400);
    }

    @ExceptionHandler({NoIdException.class})
    public ResponseEntity<ExceptionResponse> exception(NoIdException e) {
        return errorLoggingService.logExceptionAndConstructErrorResponse(e, MessagingAPIExceptionMessage.NO_ID_400);
    }

    @ExceptionHandler({PlatformSslHandshakeException.class})
    public ResponseEntity<ExceptionResponse> exception(PlatformSslHandshakeException e) {
        return errorLoggingService.logExceptionAndConstructErrorResponse(e, MessagingAPIExceptionMessage.SSL_NOT_AUTHORISED);
    }

    @ExceptionHandler({SourceNotAuthorisedException.class})
    public ResponseEntity<ExceptionResponse> exception(SourceNotAuthorisedException e) {
        return errorLoggingService.logExceptionAndConstructErrorResponse(e, MessagingAPIExceptionMessage.SSL_NOT_AUTHORISED);
    }

    @ExceptionHandler({DuplicateKeyException.class})
    public ResponseEntity<ExceptionResponse> exception(DuplicateKeyException e) {
        return errorLoggingService.logExceptionAndConstructErrorResponse(e, MessagingAPIExceptionMessage.BAD_MESSAGE_400);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        errorLoggingService.logException(ex, MessagingAPIExceptionMessage.BAD_MESSAGE_400);
        return ResponseEntity.status(status).body(new ExceptionResponse(Constants.RESPONSE_TYPE_ERROR, messageResolver.getMessage(MessagingAPIExceptionMessage.BAD_MESSAGE_400), MessagingAPIExceptionMessage.BAD_MESSAGE_400.getResponseCode()));
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        errorLoggingService.logException(ex, MessagingAPIExceptionMessage.CATCH_ALL);
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }
}


