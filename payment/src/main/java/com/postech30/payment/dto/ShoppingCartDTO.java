package com.postech30.payment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartDTO {

    private Long id;

    private List<ProductDTO> products;

    @NotBlank(message = "O id do usuário é um campo de preenchimento obrigatório")
    private Long userId;

    private BigDecimal totalValue;

}