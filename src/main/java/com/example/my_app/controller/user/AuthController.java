package com.example.my_app.controller.user;


import com.example.my_app.dto.auth.ChangePasswordRequest;
import com.example.my_app.dto.user.AuthRequest;
import com.example.my_app.dto.user.AuthenticationResponse;
import org.springframework.http.ResponseEntity;

public interface AuthController {

    ResponseEntity<AuthenticationResponse>authentication(AuthRequest request);

    ResponseEntity<Void>sendResentPasswordEmail(String email);

    ResponseEntity<String>changePassword(ChangePasswordRequest request);
}
