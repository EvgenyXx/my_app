package com.example.my_app.exception.user;

public class DuplicateNumberPhoneException extends RuntimeException {

    public DuplicateNumberPhoneException(String message) {
        super(message);
    }
}
