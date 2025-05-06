package com.example.my_app.dto.mapping;


import com.example.my_app.dto.user.*;
import com.example.my_app.entity.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserCreateRequest userCreateRequest);

    UserCreateResponse toDtoCreate(User user);

    UserSearchResponse toDtoSearch(User user);

    UserUpdateResponse toDtoUpdate(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUser(@MappingTarget User user, UserUpdateRequest userUpdateRequest);
}
