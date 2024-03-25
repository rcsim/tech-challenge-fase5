package com.postech30.msusermanager.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {

    @NotBlank(message = "usuario  é obrigatorio")
    private String username;

    @NotBlank(message = "senha  é obrigatoria")
    private String password;
}
