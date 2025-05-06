package com.example.my_app.service.card;

import com.example.my_app.dto.card.CardCreateRequest;
import com.example.my_app.dto.card.CardResponse;
import com.example.my_app.entity.Card;

import java.util.List;



public interface CardService {

    void createCard(CardCreateRequest request);



    void deleteCardById(Long cardID);

    Card findCardById(Long cardId);

    List<CardResponse> getUserCards();

    CardResponse findByCardNumber(String cardNumber);
}
