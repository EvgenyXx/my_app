package com.example.my_app.service.card;

import com.example.my_app.dto.card.CardCreateRequest;
import com.example.my_app.entity.Card;

import java.util.UUID;

public interface CardService {

    void createCard(UUID userId,CardCreateRequest request);

    void saveCard(Card card);
}
