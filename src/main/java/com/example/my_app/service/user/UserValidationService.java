package com.example.my_app.service.user;

import com.example.my_app.dto.user.UserUpdateRequest;
import com.example.my_app.entity.User;
import com.example.my_app.exception.user.DuplicateEmailException;
import com.example.my_app.exception.user.DuplicateNumberPhoneException;
import com.example.my_app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserValidationService {

    private final UserRepository userRepository;

    public void validateNumberPhone(String numberPhone) {
        if (userRepository.existsByNumberPhone(numberPhone)) {
            throw new DuplicateNumberPhoneException(
                    String.format("Номер %s уже используется в системе", numberPhone)
            );
        }
    }

    public void validateEmail(String email){
        if (userRepository.existsByEmail(email)) {
            throw new DuplicateEmailException(
                    String.format("Email %s уже используется в системе", email)
            );
        }
    }

    public void validateUserUpdate(User user, UserUpdateRequest request) {
        if (request.getEmail() != null && !request.getEmail().equals(user.getEmail())) {
            validateEmail(request.getEmail());
        }

        if (request.getNumberPhone() != null && !request.getNumberPhone().equals(user.getNumberPhone())) {
            validateNumberPhone(request.getNumberPhone());
        }
    }


}
