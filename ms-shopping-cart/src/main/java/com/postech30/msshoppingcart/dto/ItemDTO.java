package com.postech30.msshoppingcart.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {

    private int id;

    private Integer userId;

    private String name;

    private String description;

    private Integer stocktaking;

    private Double amount;

}
