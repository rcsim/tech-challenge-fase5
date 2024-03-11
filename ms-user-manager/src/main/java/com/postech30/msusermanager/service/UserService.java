package com.postech30.msusermanager.service;

import com.postech30.msusermanager.dto.UserCreateDTO;
import com.postech30.msusermanager.dto.UserViewDTO;
import com.postech30.msusermanager.entity.User;
import com.postech30.msusermanager.exception.UserDoNotFindException;
import com.postech30.msusermanager.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserViewDTO saveUser(UserCreateDTO userCreateDTO){

        String passwordCryptographic = new BCryptPasswordEncoder().encode(userCreateDTO.password());
        var user = new User();
        BeanUtils.copyProperties(userCreateDTO, user);
        user.setPassword(passwordCryptographic);

        var userSaved = userRepository.save(user);
        return new UserViewDTO(userSaved);
    }

    public List<UserViewDTO> listAllUser(){
        return userRepository
                .findAll()
                .stream()
                .map(UserViewDTO::new)
                .toList();
    }

    public UserViewDTO findUserById(Long id){
        Optional<User> userOptional =
                userRepository.findById(id);
        if (userOptional.isPresent()){
            return new UserViewDTO(userOptional.get());
        } else {
            throw new UserDoNotFindException("Usuário não encontrado na base de dados!");
        }
    }

    public void deleteUser(Long id){
        Optional<User> userOptional =
                userRepository.findById(id);
        if (userOptional.isPresent()){
            userRepository.delete(userOptional.get());
        } else {
            throw new UserDoNotFindException("Usuário não encontrado!");
        }
    }

    public User updateUser(User user){
        Optional<User> userOptional =
                userRepository.findById(user.getUserId());
        if (userOptional.isPresent()) {
            return userRepository.save(user);
        } else {
            throw new UserDoNotFindException("Usuário não encontrado!");
        }
    }
}
