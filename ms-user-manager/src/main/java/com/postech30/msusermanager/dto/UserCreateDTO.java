package com.postech30.msusermanager.dto;

import com.postech30.msusermanager.entity.enums.UserRole;

public record UserCreateDTO(

        String userId,
        String name,
        String email,
        String password,
        UserRole role
) {
}
