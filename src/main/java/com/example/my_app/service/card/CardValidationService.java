package com.example.my_app.service.card;

import com.example.my_app.exception.card.DuplicateCardNumberException;
import com.example.my_app.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardValidationService {
    private final CardRepository cardRepository;

    public void validateCardNumber(String cardNumber){
        if (cardRepository.existsByCardNumber(cardNumber)){
            throw new DuplicateCardNumberException("Карта уже используется другим пользователем");
        }
    }
}
