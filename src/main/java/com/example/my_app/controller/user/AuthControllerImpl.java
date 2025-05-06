package com.example.my_app.controller.user;

import com.example.my_app.dto.auth.ChangePasswordRequest;
import com.example.my_app.dto.user.AuthRequest;
import com.example.my_app.dto.user.AuthenticationResponse;
import com.example.my_app.service.user.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthControllerImpl implements AuthController {
    private final AuthService authService;

    @PostMapping
    @Override
    public ResponseEntity<AuthenticationResponse> authentication(
            @Valid @RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.authentication(request));
    }

    @PostMapping("/resent-password")
    @Override
    public ResponseEntity<Void> sendResentPasswordEmail(@RequestParam String email) {
        authService.resentPassword(email);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/change-password")
    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<String> changePassword(
           @RequestBody ChangePasswordRequest request) {
        return ResponseEntity.ok(authService.changePassword(request));
    }
}
