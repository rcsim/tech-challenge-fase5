package com.postech30.msusermanager.service.impl;



import com.postech30.msusermanager.dto.UserDTO;
import com.postech30.msusermanager.entity.User;
import com.postech30.msusermanager.exception.ResourceNotFoundException;
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
    public UserDTO findById(Long id) throws ResourceNotFoundException {
        User user =  userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));;
        return  UserMapper.fromEntity(user);
    }

    @Override
    public UserDTO save(UserDTO user) {
        return UserMapper.fromEntity(userRepository.save(UserMapper.toEntity(user)));
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) throws ResourceNotFoundException {
        User user =  userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));;
        mapUpdate(user,userDTO);
        userRepository.save(user);
        return UserMapper.fromEntity(user);
    }

    @Override
    public void deleteById(Long id) throws ResourceNotFoundException {
        if(!userRepository.existsById(id)){
            throw new ResourceNotFoundException("Usuário não encontrado");
        }
        userRepository.deleteById(id);
    }



    private void mapUpdate(User user, UserDTO userDTO){
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
    }
}
