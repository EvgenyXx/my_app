package com.example.my_app.service.user;

import com.example.my_app.config.jwt.JWTService;
import com.example.my_app.dto.auth.ChangePasswordRequest;
import com.example.my_app.dto.user.AuthRequest;
import com.example.my_app.dto.user.AuthenticationResponse;

import com.example.my_app.entity.User;
import com.example.my_app.exception.token.InvalidTokenException;
import com.example.my_app.exception.user.DuplicatePasswordException;
import com.example.my_app.exception.user.InvalidCredentialsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final PasswordChangeEmailService passwordChangeEmailService;

    @Override
    public AuthenticationResponse authentication(AuthRequest request) {
        User user = userService.findByNumberPhone(request.getNumberPhone());
        if (passwordEncoder.matches(request.getPassword(),user.getPassword())){
           String accessToken = jwtService.generateAccessToken(request.getNumberPhone());
           String refreshToken =
                   jwtService.generateRefreshToken(request.getNumberPhone(),
                           user.getRoles());
           return new AuthenticationResponse(refreshToken,accessToken);
        }else {
            throw new InvalidCredentialsException(
                    "Не верный номер телефона или пароль"
            );
        }
    }



    @Override
    public void resentPassword(String email) {
        User user = userService.findByEmail(email);
        String changePasswordToken =
                jwtService.generateChangePasswordToken(user.getEmail(),user.getRoles());
        passwordChangeEmailService.sendResetPasswordEmail(email,changePasswordToken);
    }

    @Override
    @Transactional
    public String changePassword(ChangePasswordRequest request) {
       String email = jwtService.getEmail(request.getToken());
       if (email == null && !jwtService.validateToken(request.getToken())){
           throw new InvalidTokenException("Не действительный токен");
       }
       User user = userService.findByEmail(email);
       if (passwordEncoder.matches(request.getNewPassword(), user.getPassword())){
           throw  new DuplicatePasswordException("Пароли совпадают");
       }else {
           user.setPassword(passwordEncoder.encode(request.getNewPassword()));
           userService.saveUser(user);
           return """
           🎉 Пароль успешно изменен!
           
           🔒 Ваш аккаунт теперь защищен новым паролем.
           ⏳ Рекомендуем не сообщать его никому.
           """;
       }
    }


}
