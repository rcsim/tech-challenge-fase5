package com.postech30.payment.service;

import com.postech30.payment.dto.CardDTO;
import com.postech30.payment.dto.PaymentDTO;
import com.postech30.payment.dto.ShoppingCartDTO;

public interface PaymentService {


    PaymentDTO checkout(ShoppingCartDTO carrinho, CardDTO cardDTO);
}
