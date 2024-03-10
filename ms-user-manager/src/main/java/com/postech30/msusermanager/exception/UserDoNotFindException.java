package com.postech30.msusermanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserDoNotFindException extends RuntimeException{
    public UserDoNotFindException(String msg) {
        super(msg);
    }
}
