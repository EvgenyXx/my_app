package com.example.my_app.service.role;

import com.example.my_app.dto.role.ResponseRole;
import com.example.my_app.entity.Role;

import java.util.List;

public interface RoleService {

    void saveRole(Role role);

    ResponseRole createRole(String roleName);

    Role findById(Long roleId);

    List<ResponseRole> getAllRoles();

    void deleteRoleById(Long roleId);

    boolean existsByRoleName(String roleName);


    Role findByRoleName(String roleName);

}
