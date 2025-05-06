package com.example.my_app.dto.user;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
public class UserCreateRequest {

    private static final String ERROR_MESSAGE = "Поле обязательно для заполнения";



    @NotBlank(message = "Номер телефона обязателен для заполнения")
    @Pattern(regexp = "^8\\d{10}$", message = "Пример номера телефона: 89181228177")
    private String numberPhone;

    @NotBlank(message = "Имя обязательно для заполнения")
    private String firstname;

    @NotBlank(message = "Фамилия обязательна для заполнения")
    private String lastname;


    @NotBlank(message = "Пароль обязателен для заполнения")
    private String password;

    @Email(message = "Проверьте правильность вашего email")
    private String email;

    @NotNull(message = "Дата рождения обязательна")
    @Past(message = "Дата рождения должна быть в прошлом")
    private LocalDate birthDate;


}
