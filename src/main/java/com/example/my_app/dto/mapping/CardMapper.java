package com.example.my_app.dto.mapping;

import com.example.my_app.dto.card.CardCreateRequest;
import com.example.my_app.dto.card.CardResponse;
import com.example.my_app.entity.Card;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CardMapper {


    Card toEntityCreate(CardCreateRequest request);

    CardResponse toDtoResponse(Card card);
}
