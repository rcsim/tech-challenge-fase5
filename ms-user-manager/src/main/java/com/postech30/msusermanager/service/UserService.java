package com.postech30.msusermanager.service;

import com.postech30.msusermanager.dto.UserDTO;
import com.postech30.msusermanager.entity.User;
import com.postech30.msusermanager.exception.UsuarioNaoEncontradoException;

import java.util.List;

public interface UserService {

    public List<UserDTO> findAll();

    public UserDTO findById(Long id) throws UsuarioNaoEncontradoException;

    public UserDTO save(UserDTO user);

    public UserDTO updateUser(Long id, UserDTO userDTO) throws UsuarioNaoEncontradoException;

    public void deleteById(Long id) throws UsuarioNaoEncontradoException;
}
