package com.postech30.msusermanager.controller;


import com.postech30.msusermanager.dto.UserDTO;
import com.postech30.msusermanager.exception.UsuarioNaoEncontradoException;
import com.postech30.msusermanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) throws UsuarioNaoEncontradoException {
        return userService.findById(id);
    }

    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO user) {
        return userService.save(user);
    }

    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable Long id, @RequestBody UserDTO user) throws UsuarioNaoEncontradoException {

        return userService.updateUser(id,user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) throws UsuarioNaoEncontradoException {
        userService.deleteById(id);
    }




}
