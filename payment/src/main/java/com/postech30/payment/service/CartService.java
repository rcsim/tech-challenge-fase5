package com.postech30.payment.service;

import com.postech30.payment.dto.CartDTO;

public interface CartService {
    CartDTO getCart(Long cartID);
}
