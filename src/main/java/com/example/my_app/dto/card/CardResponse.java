package com.example.my_app.dto.card;

import com.example.my_app.entity.CardType;

import java.time.LocalDate;

public record CardResponse(String cardNumber, LocalDate expirationDate, CardType cardType) {
}
