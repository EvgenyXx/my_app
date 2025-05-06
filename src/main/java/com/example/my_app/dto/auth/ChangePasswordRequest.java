package com.example.my_app.dto.auth;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ChangePasswordRequest {

    @NotNull
    @Size(min = 8, message = "Пароль должен быть не менее 8 символов")
    private String newPassword;

    @NotNull
    private String token;
}
