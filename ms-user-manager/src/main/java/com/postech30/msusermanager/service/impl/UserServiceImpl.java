package com.postech30.msusermanager.service.impl;

import com.postech30.msusermanager.dto.CreateUserDTO;
import com.postech30.msusermanager.dto.LoginUserDTO;
import com.postech30.msusermanager.dto.RecoverUserDTO;
import com.postech30.msusermanager.dto.RecoveryJwtTokenDTO;
import com.postech30.msusermanager.entity.User;
import com.postech30.msusermanager.entity.Role;
import com.postech30.msusermanager.repository.UserRepository;
import com.postech30.msusermanager.security.authentication.JwtTokenService;
import com.postech30.msusermanager.security.config.SecurityConfiguration;
import com.postech30.msusermanager.security.userdetail.UserDetailsImpl;
import com.postech30.msusermanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityConfiguration securityConfiguration;

    // Autentica o usuário e retorna o token
    public RecoveryJwtTokenDTO authenticateUser(LoginUserDTO loginUserDTO) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(
                        loginUserDTO.email(),
                        loginUserDTO.password());

        Authentication authentication = authenticationManager.authenticate(
                usernamePasswordAuthenticationToken);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return new RecoveryJwtTokenDTO(jwtTokenService.generateToken(userDetails));
    }

    // Cria um novo usuário
    public CreateUserDTO createUser(CreateUserDTO createUserDTO) {

        User newUser = User.builder()
                .email(createUserDTO.email())
                .password(securityConfiguration.passwordEncoder().encode(createUserDTO.password()))
                .roles(List.of(Role.builder().roleName(createUserDTO.role()).build()))
                .build();
        userRepository.save(newUser);
        return createUserDTO;
    }

    @Override
    public Page<RecoverUserDTO> searchUser(String searchUser, Pageable pageable) {
        return null;
    }

    @Override
    public RecoverUserDTO findById(Long id) {
        return null;
    }

    @Override
    public void updateUser(Long id, RecoverUserDTO recoverUserDTO) {

    }

    @Override
    public void deleteUser(Long id) {

    }

    @Override
    public List<RecoverUserDTO> findUserByRoleId(Long id) {
        return null;
    }

}
