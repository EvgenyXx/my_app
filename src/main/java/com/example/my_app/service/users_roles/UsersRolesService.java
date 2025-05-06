package com.example.my_app.service.users_roles;

import com.example.my_app.dto.role.AddRolesRequest;
import com.example.my_app.dto.role.DeleteRolesRequest;
import com.example.my_app.dto.role.UserResponseRoles;


import java.util.UUID;

public interface UsersRolesService {

    void addRoles(UUID userId, AddRolesRequest request);

    UserResponseRoles getRolesByUserId(UUID userId);

    void deleteRolesByUser(UUID userId, DeleteRolesRequest request);

}
