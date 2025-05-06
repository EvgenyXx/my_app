package com.example.my_app.controller.role;

import com.example.my_app.dto.role.ResponseRole;
import com.example.my_app.entity.Role;
import com.example.my_app.service.role.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class RoleControllerImpl implements RoleController {
    private final RoleService roleService;
    private static final String ROLE_ID_PATH = "/{roleId}";
    private static final String ROLE_ADMIN = "ROLE_ADMIN";

    @PostMapping("/create")
    @Override
    public ResponseEntity<ResponseRole> createRole(@RequestParam String roleName) {
        return new ResponseEntity<>(roleService.createRole(roleName), HttpStatus.CREATED);
    }

    @Override
    @GetMapping(ROLE_ID_PATH)
    public ResponseEntity<Role> findById(@PathVariable Long roleId) {
        return new ResponseEntity<>(roleService.findById(roleId), HttpStatus.OK);
    }

    @Override
    @GetMapping("/get-all-roles")
    public ResponseEntity<List<ResponseRole>> getAllRoles() {
        return new ResponseEntity<>(roleService.getAllRoles(), HttpStatus.OK);
    }

    @Override
    @DeleteMapping(ROLE_ID_PATH)
    public ResponseEntity<Void> deleteRoleById(@PathVariable Long roleId) {
        roleService.deleteRoleById(roleId);
        return ResponseEntity.noContent().build();
    }
}
