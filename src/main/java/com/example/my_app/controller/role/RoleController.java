package com.example.my_app.controller.role;

import com.example.my_app.dto.role.ResponseRole;
import com.example.my_app.entity.Role;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RoleController {


    ResponseEntity<ResponseRole> createRole(String roleName);

    ResponseEntity<Role> findById(Long roleId);

    ResponseEntity<List<ResponseRole>> getAllRoles();

    ResponseEntity<Void> deleteRoleById(Long roleId);


}
