package com.postech30.msshoppingcart.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_shopping_carts")
@EqualsAndHashCode
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "id")
    private List<Product> products;
    private Long userId;
    private BigDecimal totalValue;

    public ShoppingCart(List<Product> products, Long userId, BigDecimal totalValue) {
        this.products = products;
        this.userId = userId;
        this.totalValue = totalValue;
    }
}
