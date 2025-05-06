package com.example.my_app.exception.user;

public class DuplicatePasswordException extends RuntimeException {


    public DuplicatePasswordException(String message) {
        super(message);
    }
}

