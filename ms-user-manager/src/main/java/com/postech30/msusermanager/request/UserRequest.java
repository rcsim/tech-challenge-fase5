package com.postech30.msusermanager.request;

import com.postech30.msusermanager.entity.enums.UserRole;

public record UserRequest(String name, String password, UserRole role) {
}
