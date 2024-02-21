package com.postech30.msshoppingcart.service;

import com.postech30.msshoppingcart.dto.ShoppingCartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ShoppingCartService {
    ShoppingCartDTO createShoppingCart(ShoppingCartDTO shoppingCartDTO);

    Page<ShoppingCartDTO> getShoppingCarts(String search, Pageable pageable);

    ShoppingCartDTO getShoppingCartById(Long id);

    void updateShoppingCart(Long id, ShoppingCartDTO shoppingCartDTO);

    void deleteShoppingCart(Long id);
}
