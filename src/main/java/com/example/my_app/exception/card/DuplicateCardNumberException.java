package com.example.my_app.exception.card;

public class DuplicateCardNumberException extends RuntimeException {
    public DuplicateCardNumberException(String message) {
        super(message);
    }
}
