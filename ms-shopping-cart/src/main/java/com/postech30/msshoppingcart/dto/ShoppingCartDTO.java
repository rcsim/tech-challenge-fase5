package com.postech30.msshoppingcart.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @JsonProperty
    private List<ProductDTO> products;

    @JsonProperty
    @NotNull(message = "O id do usuário é um campo de preenchimento obrigatório")
    private Long userId;

    @JsonProperty
    private BigDecimal totalValue;

}
