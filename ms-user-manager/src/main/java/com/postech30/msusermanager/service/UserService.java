package com.postech30.msusermanager.service;

import com.postech30.msusermanager.dto.CreateUserDTO;
import com.postech30.msusermanager.dto.RecoverUserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    Page<RecoverUserDTO> searchUser(String searchUser, Pageable pageable);
    CreateUserDTO createUser(CreateUserDTO createUserDTO);
    RecoverUserDTO findById(Long id);
    void updateUser(Long id, RecoverUserDTO recoverUserDTO);
    void deleteUser(Long id);
    List<RecoverUserDTO> findUserByRoleId(Long id);
}
