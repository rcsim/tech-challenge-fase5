package com.postech30.msusermanager.mapper;

import com.postech30.msusermanager.dto.UserDTO;
import com.postech30.msusermanager.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserMapper {


    public static UserDTO fromEntity(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setEmail(user.getEmail());
        userDTO.setUserRole(user.getRole());
        return userDTO;
    }

    public static User toEntity(UserDTO userDTO) {
        User user = new User();
        String encryptedPassword = new BCryptPasswordEncoder().encode(userDTO.getPassword());
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setPassword(encryptedPassword);
        user.setEmail(userDTO.getEmail());
        user.setRole(userDTO.getUserRole());
        return user;
    }
}
