package com.example.my_app.dto.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AuthenticationResponse {

    private String refreshToken;
    private String accessToken;
}
