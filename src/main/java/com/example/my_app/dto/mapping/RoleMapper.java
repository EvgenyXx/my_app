package com.example.my_app.dto.mapping;


import com.example.my_app.dto.role.ResponseRole;
import com.example.my_app.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {


    ResponseRole toDto(Role role);
}

