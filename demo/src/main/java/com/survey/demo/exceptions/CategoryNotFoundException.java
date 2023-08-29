package com.survey.demo.exceptions;

import org.springframework.security.core.AuthenticationException;

public class CategoryNotFoundException extends RuntimeException{

    public CategoryNotFoundException(String msg){
        super(msg);
    }

    public CategoryNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
