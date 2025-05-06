package com.example.my_app.controller.users_roles;

import com.example.my_app.dto.role.AddRolesRequest;
import com.example.my_app.dto.role.DeleteRolesRequest;
import com.example.my_app.dto.role.UserResponseRoles;
import org.springframework.http.ResponseEntity;


import java.util.UUID;

public interface UsersRolesController {

    ResponseEntity<Void>addRoles(UUID userId, AddRolesRequest request);

    ResponseEntity<UserResponseRoles> getRolesByUserId(UUID userId);

    ResponseEntity<Void> deleteRolesByUser(UUID userId, DeleteRolesRequest request);
}
