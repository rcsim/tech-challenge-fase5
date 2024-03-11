package com.postech30.msusermanager.controller;

import com.postech30.msusermanager.dto.LoginDTO;
import com.postech30.msusermanager.dto.TokenDTO;
import com.postech30.msusermanager.dto.UserCreateDTO;
import com.postech30.msusermanager.dto.UserViewDTO;
import com.postech30.msusermanager.entity.User;
import com.postech30.msusermanager.repository.UserRepository;

import com.postech30.msusermanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginDTO userDto){
        var usernamePassword = new UsernamePasswordAuthenticationToken(userDto.email(), userDto.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        System.out.println(auth.getPrincipal());
        //String token = tokenService
//        var token = tokenService.verifyToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new TokenDTO(token));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserViewDTO register(@RequestBody @Valid UserCreateDTO userCreateDTO){

        UserViewDTO userSaved = null;
        userSaved = userService.saveUser(userCreateDTO);
        return userSaved;

//        if(this.repository.findByLogin(data.login()) != null)
//            return ResponseEntity.badRequest().build();
//
//        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
//        User newUser = new User(data.login(), encryptedPassword, data.role());
//        this.repository.save(newUser);
//        return ResponseEntity.ok().build();
    }
}
