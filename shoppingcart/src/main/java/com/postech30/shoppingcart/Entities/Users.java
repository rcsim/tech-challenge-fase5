package com.postech30.shoppingcart.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Users {
    @Id
    private Long id;

    private  String name;
}
