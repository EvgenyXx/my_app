package com.example.my_app.controller.card;

import com.example.my_app.dto.card.CardCreateRequest;
import com.example.my_app.service.card.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1/card")
@RequiredArgsConstructor
public class CardControllerImpl implements CardController {
    private final CardService cardService;

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    @Override
    public ResponseEntity<Void> createCard(
            @RequestBody CardCreateRequest request
            ) {
        cardService.createCard(request);
        return ResponseEntity.ok().build();
    }
}
