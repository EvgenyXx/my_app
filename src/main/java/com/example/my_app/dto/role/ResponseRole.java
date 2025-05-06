package com.example.my_app.dto.role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ResponseRole {

    private Long id;

    private String roleName;
}
