package com.iba.ipr.exception.forbidden;

import com.iba.ipr.util.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class IncorrectCredentialsException extends ResponseStatusException {
    public IncorrectCredentialsException() {
        super(HttpStatus.FORBIDDEN, Constants.INCORRECT_CREDENTIALS_MESSAGE);
    }
}