package com.example.my_app.service.user;


import com.example.my_app.dto.mapping.UserMapper;
import com.example.my_app.dto.user.*;
import com.example.my_app.entity.User;
import com.example.my_app.exception.user.UserNotFoundException;
import com.example.my_app.repository.UserRepository;
import com.example.my_app.service.role.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final UserValidationService validationService;
    private final UserMapper userMapper;
    private static final String USER_NOT_FOUND_MESSAGE = "Пользователь не найден";

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public UserCreateResponse createUser(UserCreateRequest request) {
        validationService.validateEmail(request.getEmail());
        validationService.validateNumberPhone(request.getNumberPhone());
        User user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of(roleService.findByRoleName("ROLE_USER")));
        return userMapper.toDtoCreate(saveUser(user));
    }

    @Override
    public User findUserById(UUID uuid) {
        return userRepository.findById(uuid).orElseThrow(() ->
                new UserNotFoundException(USER_NOT_FOUND_MESSAGE));
    }

    @Override
    public List<UserSearchResponse> findByFirstnameStartingWithOrderByFirstnameDesc(String firstname) {
        return userRepository.findByFirstnameStartingWithOrderByFirstnameDesc(firstname)
                .stream()
                .map(userMapper::toDtoSearch)
                .toList();
    }

    @Override
    @Transactional
    public void deleteUserById(UUID userId) {
        userRepository.deleteById(userId);
    }

    @Override
    @Transactional
    public UserUpdateResponse updateUser(UUID userId, UserUpdateRequest userUpdateRequest) {
        //Находим пользователя по айди
        User user = findUserById(userId);
        validationService.validateUserUpdate(user,userUpdateRequest);

        if (userUpdateRequest.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userUpdateRequest.getPassword()));
        }

        //обновляем поля через mapper
        userMapper.updateUser(user, userUpdateRequest);

        return userMapper.toDtoUpdate(saveUser(user));
    }



    @Override
    public User findByNumberPhone(String numberPhone) {
        return userRepository.findByNumberPhone(numberPhone).orElseThrow(
                () -> new UserNotFoundException(USER_NOT_FOUND_MESSAGE)
        );

    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new UserNotFoundException(USER_NOT_FOUND_MESSAGE)
        );
    }

    @Override
    public int getAgeUser(UUID userId) {
        User user = findUserById(userId);
        return user.getAge();
    }
}
