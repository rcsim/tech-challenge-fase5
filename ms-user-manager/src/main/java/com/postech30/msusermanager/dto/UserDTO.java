package com.postech30.msusermanager.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NotBlank(message = "usuario é obrigatorio")
    private String username;

    @NotBlank(message = "Senha é obrigatorio")
    private String password;

    @Email(message = "Email tem que ser valido")
    @NotBlank(message = "Email é obrigatorio")
    private String email;
}
