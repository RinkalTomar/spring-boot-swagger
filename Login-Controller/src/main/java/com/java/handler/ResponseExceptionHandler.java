package com.java.handler;


import com.java.beans.response.AbstractResponse;
import com.java.enums.Enumeration;
import com.java.exception.BadRequestException;
import com.java.exception.LoginAuthenticationException;
import com.java.exception.LoginAuthorizationException;
import com.java.exception.LoginException;
import com.java.message.MessageProperties;
import com.java.services.ICoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@ControllerAdvice
@RestController
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    MessageProperties _messages;

    @Autowired
    ICoreService _coreService ;

    @ExceptionHandler({Exception.class, LoginException.class})
    public final ResponseEntity<AbstractResponse> handleAllExceptions(Exception ex) {
        ex.printStackTrace();
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put(Enumeration.ErrorCode.INTERNAL_SERVER_ERROR_500.toString(), ex.getMessage() != null ? ex.getMessage() : _messages.getMessage("unexpected.error.message"));
        AbstractResponse errorDetails = new AbstractResponse(
                false, _coreService.isAuthenticated(), _messages.getMessage("unexpected.error"), errorMap);
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(LoginAuthenticationException.class)
    public final ResponseEntity<AbstractResponse> handleLoginAuthenticationException(LoginAuthenticationException ex) {

        ex.printStackTrace();
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put(Enumeration.ErrorCode.UNAUTHORIZED_401.toString(), ex.getMessage());
        AbstractResponse errorDetails = new AbstractResponse(
                false, _coreService.isAuthenticated(), _messages.getMessage("authentication.failed"), errorMap);
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);

    }

    @ExceptionHandler({LoginAuthorizationException.class, AccessDeniedException.class})
    public final ResponseEntity<AbstractResponse> handleGreatGamesAuthorizationException(Exception ex) {

        ex.printStackTrace();
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put(Enumeration.ErrorCode.FORBIDDEN_403.toString(), ex.getMessage());
        AbstractResponse errorDetails = new AbstractResponse(
                false, _coreService.isAuthenticated(), _messages.getMessage("authorization.failed"), errorMap);
        return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({NoSuchElementException.class, BadRequestException.class})
    public final ResponseEntity<AbstractResponse> handleInvalidIndentifierException(Exception ex) {

        ex.printStackTrace();
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put(Enumeration.ErrorCode.BAD_REQEST_400.toString(), ex.getMessage());
        AbstractResponse errorDetails = new AbstractResponse(
                false, _coreService.isAuthenticated(), _messages.getMessage("bad.request"), errorMap);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
