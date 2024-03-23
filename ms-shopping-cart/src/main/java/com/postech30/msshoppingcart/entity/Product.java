package com.postech30.msshoppingcart.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Table(name = "tb_products")
@EqualsAndHashCode
public class Product {

    private long productId;
    private String name;
    private BigDecimal price;
    private int quantity;

    public Product(Long productId, String name, BigDecimal price, int quantity) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}
