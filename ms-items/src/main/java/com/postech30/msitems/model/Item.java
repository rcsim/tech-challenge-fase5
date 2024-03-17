package com.postech30.msitems.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "ID do usuário não pode ser vazio/nulo.")
    private Integer userId;

    @NotBlank(message = "Nome não pode ser vazio/nulo.")
    @Size(min = 1, max = 20, message = "Nome deve ter entre 1 e 20 caracteres.")
    private String name;

    @Size(min = 0, max = 200, message = "Descrição deve ter no máximo 200 caracteres.")
    private String description;

    @NotNull(message = "Quantidade em estoque não pode ser vazio/nulo.")
    private Integer stocktaking;

    @NotNull(message = "Valor não pode ser nulo.")
    private Double amount;
}
