package com.example.my_app.dto.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
public class UserSearchResponse {

    private UUID id;

    private String numberPhone;

    private String firstname;

    private String lastname;
}
