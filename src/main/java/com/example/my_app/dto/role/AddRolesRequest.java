package com.example.my_app.dto.role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class AddRolesRequest {

    private List<Long> rolesId;
}
