package com.postech30.msshoppingcart.repository;

import com.postech30.msshoppingcart.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    ShoppingCart findByUserId(Long userId);
}
