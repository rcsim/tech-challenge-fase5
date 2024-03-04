package com.postech30.msusermanager.dto;

import com.postech30.msusermanager.entity.User;

public record UserViewDTO(

        String userId,
        String name,
        String email
) {

    public UserViewDTO(User user){
        this(
                user.getUserId(),
                user.getName(),
                user.getEmail()
        );
    }
}
