package com.example.my_app.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordChangeEmailService {

    private final JavaMailSender javaMailSender;
    private static final String FRONTEND_URL = "https://your-frontend-app.com"; // Замените на реальный URL


    public void sendResetPasswordEmail(String email, String token) {
        String resetLink = FRONTEND_URL + "/reset-password?token=" + token;

        String body = "Здравствуйте!\n\n"
                + "Мы получили запрос на восстановление пароля для вашего аккаунта.\n"
                + "Чтобы сбросить пароль, пожалуйста, перейдите по следующей ссылке:\n\n"
                + resetLink + "\n\n"
                + "Если вы не запрашивали сброс пароля, проигнорируйте это письмо.\n\n"
                + "Этот токен будет действителен в течение 24 часов.\n\n"
                + "С уважением,\n"
                + "Команда вашего приложения \n"
                + token;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("evgenypavlov666@yandex.ru");
        message.setTo(email);
        message.setSubject("Восстановление пароля");
        message.setText(body);
        javaMailSender.send(message);
    }
}
