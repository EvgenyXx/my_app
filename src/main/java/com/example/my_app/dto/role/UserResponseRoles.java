package com.example.my_app.dto.role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class UserResponseRoles {

    private String firstname;

    private List<ResponseRole>responseRoles;
}
