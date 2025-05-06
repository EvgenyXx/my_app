package com.example.my_app.dto.role;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


import java.util.Set;

@Data
@AllArgsConstructor
@Builder
public class DeleteRolesRequest {


    private Set<Long> rolesId;
}
