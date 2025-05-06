package com.example.my_app.service.card;

import com.example.my_app.dto.card.CardCreateRequest;
import com.example.my_app.dto.mapping.CardMapper;
import com.example.my_app.entity.Card;
import com.example.my_app.entity.User;
import com.example.my_app.repository.CardRepository;
import com.example.my_app.service.user.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;
    private final CardMapper cardMapper;
    private final CardValidationService cardValidationService;
    private final UserService userService;

    @Override
    @Transactional
    public void createCard(UUID userId,CardCreateRequest request) {


        User user = userService.findUserById(userId);
        cardValidationService.validateCardNumber(request.getCardNumber());
        Card card = cardMapper.toEntityCreate(request);
        user.getCards().add(card);
        card.setUser(user);
        saveCard(card);
        userService.saveUser(user);
    }

    @Override
    public void saveCard(Card card) {
        cardRepository.save(card);
    }
}
