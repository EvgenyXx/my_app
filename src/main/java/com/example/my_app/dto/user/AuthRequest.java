package com.example.my_app.dto.user;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AuthRequest {

    @NotBlank(message = "Номер телефона обязателен для заполнения")
    @Pattern(regexp = "^8\\d{10}$", message = "Пример номера телефона: 89181228177")
    private String numberPhone;

    @NotBlank(message = "Пароль обязателен для заполнения")
    private String password;


}
