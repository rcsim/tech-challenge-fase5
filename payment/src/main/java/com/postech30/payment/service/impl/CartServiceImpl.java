package com.postech30.payment.service.impl;

import com.postech30.payment.dto.ShoppingCartDTO;
import com.postech30.payment.service.CartService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CartServiceImpl  implements CartService {

    @Value("${endpoint.cart}")
    private String endpoint;
    @Override
    public ShoppingCartDTO getCart(Long cartID) {

        RestTemplate rest = new RestTemplate();
        var response =  rest.getForEntity(endpoint+cartID, ShoppingCartDTO.class);

        return response.getBody();
    }
}
