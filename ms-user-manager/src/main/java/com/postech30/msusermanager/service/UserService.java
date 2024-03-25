package com.postech30.msusermanager.service;

import com.postech30.msusermanager.dto.UserDTO;

import com.postech30.msusermanager.exception.ResourceNotFoundException;

import java.util.List;

public interface UserService {

    public List<UserDTO> findAll();

    public UserDTO findById(Long id) throws ResourceNotFoundException;

    public UserDTO save(UserDTO user);

    public UserDTO updateUser(Long id, UserDTO userDTO) throws ResourceNotFoundException;

    public void deleteById(Long id) throws ResourceNotFoundException;
}
