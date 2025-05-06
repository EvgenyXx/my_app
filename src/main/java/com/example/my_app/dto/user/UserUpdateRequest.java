package com.example.my_app.dto.user;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class UserUpdateRequest {

    private UUID id;


    private String email;


    @Pattern(regexp = "^8\\d{10}$", message = "Пример номера телефона: 89181228177")
    private String numberPhone;



    private String firstname;


    private String password;


    private String lastname;


}
