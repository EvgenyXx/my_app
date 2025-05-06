package com.example.my_app.controller.user;

import com.example.my_app.dto.user.*;

import com.example.my_app.entity.User;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface UserController {


    ResponseEntity<UserCreateResponse>createUser(UserCreateRequest userCreateRequest);

    ResponseEntity<List<UserSearchResponse>>findByFirstnameStartingWithOrderByFirstnameDesc(String firstname);

    ResponseEntity<Void>deleteUserById(UUID userId);

    ResponseEntity<UserUpdateResponse>updateUser(UUID userId, UserUpdateRequest userUpdateRequest);

    ResponseEntity<User>findByEmail(String email);

    ResponseEntity<Integer> getAgeUser(UUID userId);
}
