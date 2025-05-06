package com.example.my_app.service.user;


import com.example.my_app.dto.auth.ChangePasswordRequest;
import com.example.my_app.dto.user.AuthRequest;
import com.example.my_app.dto.user.AuthenticationResponse;


public interface AuthService {

    AuthenticationResponse authentication (AuthRequest request);

    void resentPassword(String email);

    String changePassword(ChangePasswordRequest request);



}
