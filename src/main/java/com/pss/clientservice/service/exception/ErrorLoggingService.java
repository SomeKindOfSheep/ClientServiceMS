package com.pss.clientservice.service.exception;

import com.pss.clientservice.service.entity.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@Slf4j
public class ErrorLoggingService {

    private final MessagingAPII18nMessageResolver messageResolver;

    private final HttpServletRequest httpServletRequest;

    @Autowired
    public ErrorLoggingService(MessagingAPII18nMessageResolver messageResolver, HttpServletRequest httpServletRequest) {
        this.messageResolver = messageResolver;
        this.httpServletRequest = httpServletRequest;
    }

    public ResponseEntity<ExceptionResponse> logExceptionAndConstructErrorResponse(Exception e, MessagingAPIExceptionMessage errorCode) {
        errorCode = (errorCode == null) ? MessagingAPIExceptionMessage.CATCH_ALL : errorCode;
        logException(e, errorCode);
        return ResponseEntity.status(errorCode.getHttpStatus()).body(new ExceptionResponse(Constants.RESPONSE_TYPE_ERROR, messageResolver.getMessage(errorCode), errorCode.getResponseCode()));
    }
        // this we can use for a f
  public void logException(Exception e, MessagingAPIExceptionMessage errorCode) {
        log.trace("Global exception hangler {}", errorCode, e);
        String exceptionMessage = (e == null || StringUtils.isBlank(e.getMessage())) ? messageResolver.getMessage(errorCode) : e.getMessage();
        errorCode = (errorCode == null) ? MessagingAPIExceptionMessage.CATCH_ALL : errorCode;
        String stackTrace = (e == null) ? null : ExceptionUtils.getStackTrace(e);
        // insert your own logger here
    }


}
