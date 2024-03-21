package com.postech30.msshoppingcart.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    private Long id;

    @JsonProperty
    @NotBlank(message = "O nome do produto é um campo de preenchimento obrigatório")
    private String name;

    @JsonProperty
    @NotNull(message = "O valor do produto é um campo de preenchimento obrigatório")
    private BigDecimal price;

    @JsonProperty
    @NotNull(message = "A quantidade do produto é um campo de preenchimento obrigatório")
    private int quantity;

}
