package com.postech30.msusermanager.dto;

import com.postech30.msusermanager.entity.Role;

import java.util.List;

public record RecoverUserDTO(Long id, String email, List<Role> roleList) {
}
