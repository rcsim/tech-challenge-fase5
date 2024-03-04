package com.postech30.msusermanager.service;

import com.postech30.msusermanager.dto.UserCreateDTO;
import com.postech30.msusermanager.dto.UserViewDTO;
import com.postech30.msusermanager.entity.User;
import com.postech30.msusermanager.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

}
gi