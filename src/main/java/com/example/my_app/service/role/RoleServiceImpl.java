package com.example.my_app.service.role;

import com.example.my_app.dto.mapping.RoleMapper;
import com.example.my_app.dto.role.ResponseRole;
import com.example.my_app.entity.Role;
import com.example.my_app.exception.role.DuplicateRoleNameException;
import com.example.my_app.exception.role.RoleNotFoundException;
import com.example.my_app.repository.RoleRepository;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private static final String ROLE_NOT_FOUND = "Роль не найдена";

    @Override
    public void saveRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public ResponseRole createRole(@NotBlank(message = "Имя роли не может быть пустым "
    ) String roleName) {
       if (existsByRoleName(roleName)){
           throw new DuplicateRoleNameException("Такая роль уже существует");
       }
        Role role = Role.builder()
                .roleName("ROLE_" + roleName)
                .build();
        saveRole(role);
        return roleMapper.toDto(role);
    }

    @Override
    public boolean existsByRoleName(String roleName) {
        return roleRepository.existsByRoleName(roleName);
    }

    @Override
    public Role findById(Long roleId) {
        return roleRepository.findById(roleId).orElseThrow(
                () -> new RoleNotFoundException(ROLE_NOT_FOUND)
        );
    }

    @Override
    public List<ResponseRole> getAllRoles() {
        return roleRepository.findAll()
                .stream()
                .map(role -> ResponseRole.builder()
                        .id(role.getId())
                        .roleName(role.getRoleName())
                        .build()
                )
                .toList();
    }

    @Override
    public void deleteRoleById(Long roleId) {
        roleRepository.deleteById(roleId);
    }

    @Override
    public Role findByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName).orElseThrow(
                ()-> new RoleNotFoundException(ROLE_NOT_FOUND)
        );
    }
}
