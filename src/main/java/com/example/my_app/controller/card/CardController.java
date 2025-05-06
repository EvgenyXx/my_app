package com.example.my_app.controller.card;

import com.example.my_app.dto.card.CardCreateRequest;
import org.springframework.http.ResponseEntity;



public interface CardController {

    ResponseEntity<Void>createCard(CardCreateRequest request);
}
