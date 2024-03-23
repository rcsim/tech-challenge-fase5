package com.postech30.msshoppingcart.utils;

import com.postech30.msshoppingcart.dto.ShoppingCartDTO;

import java.math.BigDecimal;

public class ShoppingCartUtils {

    public static BigDecimal CalculateTotalValue(ShoppingCartDTO shoppingCartDTO) {
        BigDecimal totalValue = BigDecimal.valueOf(shoppingCartDTO.getProducts().stream()
                .mapToDouble(product -> product.getPrice().doubleValue() * product.getQuantity())
                .sum());
        shoppingCartDTO.setTotalValue(totalValue);

        return  totalValue;
    }

}
