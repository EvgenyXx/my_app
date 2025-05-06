package com.example.my_app.exception.card;

public class CardAccessDeniedException extends RuntimeException {
    public CardAccessDeniedException(String message) {
        super(message);
    }
}
