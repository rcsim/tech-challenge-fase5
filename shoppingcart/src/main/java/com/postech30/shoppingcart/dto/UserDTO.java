package com.postech30.shoppingcart.dto;

import com.postech30.shoppingcart.entities.User;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String name;

    public  UserDTO(User entity) {
        id = entity.getId();
        name = entity.getName();
    }
}
