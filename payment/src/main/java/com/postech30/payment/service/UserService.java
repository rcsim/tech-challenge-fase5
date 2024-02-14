package com.postech30.payment.service;

import com.postech30.payment.dto.UserDTO;

public interface UserService {
    UserDTO getUser(Long usuerId);
}
