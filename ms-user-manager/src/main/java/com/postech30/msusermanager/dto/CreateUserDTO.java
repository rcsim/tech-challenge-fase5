package com.postech30.msusermanager.dto;

import com.postech30.msusermanager.entity.User;
import com.postech30.msusermanager.enums.UserRole;

public record CreateUserDTO(String email, String password, UserRole role) {

}
