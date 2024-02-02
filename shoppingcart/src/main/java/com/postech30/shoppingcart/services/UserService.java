package com.postech30.shoppingcart.services;

import com.postech30.shoppingcart.dto.UserDTO;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);
    UserDTO findById(Long id);

}
