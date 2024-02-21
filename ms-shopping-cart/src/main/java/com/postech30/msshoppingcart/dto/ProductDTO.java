package com.postech30.msshoppingcart.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty
    @NotBlank(message = "O id do produto é um campo de preenchimento obrigatório")
    private Long id;
    @JsonProperty
    @NotBlank(message = "O nome do produto é um campo de preenchimento obrigatório")
    private String name;
    @JsonProperty
    @NotBlank(message = "O valor do produto é um campo de preenchimento obrigatório")
    private BigDecimal price;
    @JsonProperty
    @NotBlank(message = "A quantidade do produto é um campo de preenchimento obrigatório")
    private int quantity;

}
