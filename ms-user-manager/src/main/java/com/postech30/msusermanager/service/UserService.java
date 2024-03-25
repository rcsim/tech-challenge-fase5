package com.postech30.msusermanager.service;

import com.postech30.msusermanager.dto.UserDTO;
<<<<<<< HEAD
=======
import com.postech30.msusermanager.entity.User;
import com.postech30.msusermanager.exception.AuthenticateException;
>>>>>>> 9d27c9b989d934d18e88bdefd9187180925e5478
import com.postech30.msusermanager.exception.ResourceNotFoundException;

import java.util.List;

public interface UserService {

    public List<UserDTO> findAll();

    public UserDTO findById(Long id) throws ResourceNotFoundException;

    public UserDTO save(UserDTO user);

    public UserDTO updateUser(Long id, UserDTO userDTO) throws ResourceNotFoundException;

    public void deleteById(Long id) throws ResourceNotFoundException;
}
