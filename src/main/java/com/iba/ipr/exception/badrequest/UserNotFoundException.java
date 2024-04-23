package com.iba.ipr.exception.badrequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserNotFoundException extends ResponseStatusException {
    public UserNotFoundException(long userId) {
        super(HttpStatus.BAD_REQUEST, String.format("User with id '%s' was not found", userId));
    }

    public UserNotFoundException(String email) {
        super(HttpStatus.BAD_REQUEST, String.format("User with id '%s' was not found", email));
    }
}