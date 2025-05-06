package com.example.my_app.dto.card;


import com.example.my_app.entity.CardType;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class CardCreateRequest {

    @NotNull(message = "Card number cannot be null")
    @Pattern(regexp = "^[0-9]{16}$", message = "Card number must be 16 digits")
    private String cardNumber;

    @NotNull(message = "Expiration date cannot be null")
    @FutureOrPresent(message = "Expiration date must be in the present or future")
    private LocalDate expirationDate;

    @NotNull(message = "Card type cannot be null")
    private CardType cardType;
}
