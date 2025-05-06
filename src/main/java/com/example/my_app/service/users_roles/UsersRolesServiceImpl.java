package com.example.my_app.service.users_roles;

import com.example.my_app.dto.role.AddRolesRequest;
import com.example.my_app.dto.role.DeleteRolesRequest;
import com.example.my_app.dto.role.ResponseRole;
import com.example.my_app.dto.role.UserResponseRoles;
import com.example.my_app.entity.Role;
import com.example.my_app.entity.User;
import com.example.my_app.exception.role.NullException;
import com.example.my_app.exception.role.RoleNotFoundException;
import com.example.my_app.service.role.RoleService;
import com.example.my_app.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UsersRolesServiceImpl implements UsersRolesService {
    private final UserService userService;
    private final RoleService roleService;

    @Override
    public void addRoles(UUID userId, AddRolesRequest request) {
        if (request.getRolesId().isEmpty() ){
            throw new NullException("Роли не выбраны");
        }
        User user = userService.findUserById(userId);
        List<Role>roles = user.getRoles();

        for (Long roleId : request.getRolesId()){
          Role role = roleService.findById(roleId);
          if (!roles.contains(role)){
              roles.add(role);
          }
        }
        user.setRoles(roles);
        userService.saveUser(user);
    }

    @Override
    public UserResponseRoles getRolesByUserId(UUID userId) {
        User findByUserId = userService.findUserById(userId);
        List<ResponseRole>roles = findByUserId.getRoles()
                .stream()
                .map(role -> new ResponseRole(role.getId(), role.getRoleName()))
                .toList();

        return UserResponseRoles.builder()
                .firstname(findByUserId.getFirstname())
                .responseRoles(roles)
                .build();
    }

    @Override
    @Transactional
    public void deleteRolesByUser(UUID userId, DeleteRolesRequest request) {
        User findUserById = userService.findUserById(userId);
        List<Role>roles = findUserById.getRoles();
       boolean result =
               roles.removeIf
                       (role -> request.getRolesId().contains(role.getId()));
       if (!result){
           throw new RoleNotFoundException("Роли отсутствуют");
       }
    }
}
