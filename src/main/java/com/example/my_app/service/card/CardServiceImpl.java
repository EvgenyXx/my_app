package com.example.my_app.service.card;


import com.example.my_app.dto.card.CardCreateRequest;
import com.example.my_app.dto.card.CardResponse;
import com.example.my_app.dto.mapping.CardMapper;
import com.example.my_app.entity.Card;
import com.example.my_app.entity.User;
import com.example.my_app.exception.card.CardAccessDeniedException;
import com.example.my_app.exception.card.CardNotFoundException;
import com.example.my_app.repository.CardRepository;
import com.example.my_app.service.SecurityContextService;
import com.example.my_app.service.user.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;
    private final CardMapper cardMapper;
    private final CardValidationService cardValidationService;
    private final UserService userService;
    private final SecurityContextService securityContextService;

    @Override
    @Transactional
    public void createCard(CardCreateRequest request) {
        User user = userService.findUserById(securityContextService.getCurredUserId());
        cardValidationService.validateCardNumber(request.getCardNumber());
        Card card = cardMapper.toEntityCreate(request);
        user.getCards().add(card);
        card.setUser(user);
        cardRepository.save(card);
    }


    @Override
    public Card findCardById(Long cardId) {
        return cardRepository.findById(cardId).orElseThrow(() ->
                new CardNotFoundException("Карта не найдена"));
    }

    @Override
    @Transactional
    public void deleteCardById(Long cardID) {
        UUID userId = securityContextService.getCurredUserId();
        Card card = findCardById(cardID);

        if (!card.getUser().getId().equals(userId)) {
            throw new CardAccessDeniedException(
                    "У вас нет прав для выполнения данного действия"
            );
        }
        cardRepository.delete(card);
    }

    @Override
    public List<CardResponse> getUserCards() {
        UUID userId = securityContextService.getCurredUserId();
        List<Card> cards = cardRepository.findByUserId(userId);
        return cards.stream().map(
                cardMapper::toDtoResponse).toList();
    }

    @Override
    public CardResponse findByCardNumber(String cardNumber) {
        return cardRepository.findByCardNumber(cardNumber)
                .map(cardMapper::toDtoResponse)
                .orElseThrow(() -> new CardNotFoundException("Карта отсутствует"));
    }
}
