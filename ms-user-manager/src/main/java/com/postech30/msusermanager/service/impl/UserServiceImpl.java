package com.postech30.msusermanager.service.impl;



import com.postech30.msusermanager.dto.UserDTO;
import com.postech30.msusermanager.entity.User;
import com.postech30.msusermanager.exception.AuthenticateException;
import com.postech30.msusermanager.exception.UsuarioNaoEncontradoException;
import com.postech30.msusermanager.mapper.UserMapper;
import com.postech30.msusermanager.repository.UserRepository;
import com.postech30.msusermanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(UserMapper::fromEntity).collect(Collectors.toList());
    }

    @Override
    public UserDTO findById(Long id) throws UsuarioNaoEncontradoException {
        User user =  userRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException("Usuario não encontrado"));;
        return  UserMapper.fromEntity(user);
    }

    @Override
    public UserDTO save(UserDTO user) {
        return UserMapper.fromEntity(userRepository.save(UserMapper.toEntity(user)));
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) throws UsuarioNaoEncontradoException {
        User user =  userRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException("Usuario não encontrado"));;
        mapUpdate(user,userDTO);
        userRepository.save(user);
        return UserMapper.fromEntity(user);
    }

    @Override
    public void deleteById(Long id) throws UsuarioNaoEncontradoException {
        if(!userRepository.existsById(id)){
            throw new UsuarioNaoEncontradoException("Usuario não encontrado");
        }
        userRepository.deleteById(id);
    }



    private void mapUpdate(User user, UserDTO userDTO){
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
    }
}
