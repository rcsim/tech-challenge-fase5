package com.postech30.msusermanager.controller;

import com.postech30.msusermanager.dto.CreateUserDTO;
import com.postech30.msusermanager.dto.LoginUserDTO;
import com.postech30.msusermanager.dto.RecoveryJwtTokenDTO;
import com.postech30.msusermanager.entity.User;
import com.postech30.msusermanager.repository.UserRepository;
import com.postech30.msusermanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<RecoveryJwtTokenDTO> authenticateUser(
            @RequestBody LoginUserDTO loginUserDTO) {
        RecoveryJwtTokenDTO tokenDTO = userService.authenticateUser(loginUserDTO);
        return new ResponseEntity<>(tokenDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createUser(
            @RequestBody CreateUserDTO createUserDTO) {
        userService.createUser(createUserDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/test")
    public ResponseEntity<String> getAuthenticationTest() {
        return new ResponseEntity<>("Autenticado com sucesso!", HttpStatus.OK);
    }

    @GetMapping("/test/customer")
    public ResponseEntity<String> getCustomerAuthenticationTest() {
        return new ResponseEntity<>("Cliente autenticado com sucesso!", HttpStatus.OK);
    }

    @GetMapping("/test/administrador")
    public ResponseEntity<String> getAdminAuthenticationTest() {
        return new ResponseEntity<>("Administrador autenticado com sucesso!", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getAllUsers(){
        List<User> users = this.userRepository.findAll();
        return ResponseEntity.ok(users);
    }
}
