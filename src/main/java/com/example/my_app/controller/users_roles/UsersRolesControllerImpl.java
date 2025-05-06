package com.example.my_app.controller.users_roles;

import com.example.my_app.dto.role.AddRolesRequest;
import com.example.my_app.dto.role.DeleteRolesRequest;
import com.example.my_app.dto.role.UserResponseRoles;
import com.example.my_app.service.users_roles.UsersRolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users-roles")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class UsersRolesControllerImpl implements UsersRolesController {
    private final UsersRolesService usersRolesService;
    private static final String USER_ID_PATH = "/{userId}";

    @Override
    @PostMapping("/add" + USER_ID_PATH)
    public ResponseEntity<Void> addRoles(
            @PathVariable UUID userId,
            @RequestBody AddRolesRequest request) {
        usersRolesService.addRoles(userId, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping(USER_ID_PATH)
    @Override
    public ResponseEntity<UserResponseRoles> getRolesByUserId(@PathVariable UUID userId) {
        return new ResponseEntity<>
                (usersRolesService.getRolesByUserId(userId), HttpStatus.OK);
    }

    @DeleteMapping("/delete" + USER_ID_PATH)
    @Override
    public ResponseEntity<Void> deleteRolesByUser(
            @PathVariable UUID userId,
            @RequestBody DeleteRolesRequest request) {
        usersRolesService.deleteRolesByUser(userId, request);
        return ResponseEntity.noContent().build();
    }
}
