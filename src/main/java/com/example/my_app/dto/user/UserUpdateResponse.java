package com.example.my_app.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class UserUpdateResponse {


    private UUID id;


    private String email;


    private String numberPhone;



    private String firstname;


    private String password;


    private String lastname;



    private LocalDateTime createAt;


    private LocalDateTime updateAt;
}
