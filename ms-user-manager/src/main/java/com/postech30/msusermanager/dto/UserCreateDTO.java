package com.postech30.msusermanager.dto;

import com.postech30.msusermanager.entity.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserCreateDTO(

        String userId,

        @NotBlank(message = "O nome de usuário é obrigatório!")
        String name,

        @NotBlank(message = "O e-mail é obrigatório!")
        @Email(message = "O e-mail não é válido!")
        String email,

        @NotBlank(message = "A senha é obrigatória!")
        @Size(min = 6, max = 16, message = "A senha deve conter entre 6 e 16 caracteres!")
        String password,
        UserRole role
) {
}
