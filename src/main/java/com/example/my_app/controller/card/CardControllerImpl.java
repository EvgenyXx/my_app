package com.example.my_app.controller.card;

import com.example.my_app.dto.card.CardCreateRequest;
import com.example.my_app.service.card.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/card")
@RequiredArgsConstructor
public class CardControllerImpl implements CardController {
    private final CardService cardService;
    private static final String USER_ID_PATH = "/{userId}";

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @PostMapping("/create" + USER_ID_PATH)
    @Override
    public ResponseEntity<Void> createCard(
            @PathVariable UUID userId,
            @RequestBody CardCreateRequest request
            ) {
        cardService.createCard(userId, request);
        return ResponseEntity.ok().build();
    }
}
