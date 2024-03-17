package com.postech30.payment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    @NotBlank(message = "O id do produto é um campo de preenchimento obrigatório")
    private Long id;
    @NotBlank(message = "O nome do produto é um campo de preenchimento obrigatório")
    private String name;
    @NotBlank(message = "O valor do produto é um campo de preenchimento obrigatório")
    private BigDecimal price;
    @NotBlank(message = "A quantidade do produto é um campo de preenchimento obrigatório")
    private int quantity;

}