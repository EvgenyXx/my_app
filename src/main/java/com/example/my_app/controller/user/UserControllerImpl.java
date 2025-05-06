package com.example.my_app.controller.user;

import com.example.my_app.dto.user.*;
import com.example.my_app.entity.User;
import com.example.my_app.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {
    private final UserService userService;
    private static final String USER_ID_PATH = "/{userId}";

    @PostMapping("/register")
    @Override
    public ResponseEntity<UserCreateResponse> createUser(
            @Valid @RequestBody UserCreateRequest userCreateRequest) {
        return new ResponseEntity<>(userService.createUser(userCreateRequest), HttpStatus.CREATED);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<UserSearchResponse>> findByFirstnameStartingWithOrderByFirstnameDesc(
            @RequestParam String firstname) {
        return new ResponseEntity<>(
                userService.findByFirstnameStartingWithOrderByFirstnameDesc(firstname), HttpStatus.OK);
    }

    @Override
    @DeleteMapping(USER_ID_PATH)
    public ResponseEntity<Void> deleteUserById(@PathVariable UUID userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(USER_ID_PATH)
    @Override
    public ResponseEntity<UserUpdateResponse> updateUser(
            @PathVariable UUID userId, @RequestBody UserUpdateRequest userUpdateRequest) {
        return new ResponseEntity<>(userService.updateUser(userId, userUpdateRequest), HttpStatus.OK);
    }

    @GetMapping("/email")
    @Override
    public ResponseEntity<User> findByEmail(@RequestParam String email) {
        return ResponseEntity.ok(userService.findByEmail(email));
    }

    @GetMapping("/get-age" + USER_ID_PATH)
    @Override
    public ResponseEntity<Integer> getAgeUser(@PathVariable UUID userId) {
        return ResponseEntity.ok(userService.getAgeUser(userId));
    }
}
