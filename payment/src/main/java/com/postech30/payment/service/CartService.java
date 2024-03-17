package com.postech30.payment.service;

import com.postech30.payment.dto.ShoppingCartDTO;

public interface CartService {
    ShoppingCartDTO getCart(Long cartID);
}
