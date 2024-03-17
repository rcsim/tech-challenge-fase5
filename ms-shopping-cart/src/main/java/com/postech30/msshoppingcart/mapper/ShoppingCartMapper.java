package com.postech30.msshoppingcart.mapper;

import com.postech30.msshoppingcart.dto.ProductDTO;
import com.postech30.msshoppingcart.dto.ShoppingCartDTO;
import com.postech30.msshoppingcart.entity.Product;
import com.postech30.msshoppingcart.entity.ShoppingCart;

import java.util.List;
import java.util.stream.Collectors;

public class ShoppingCartMapper {

    public static ShoppingCartDTO toDTO(ShoppingCart shoppingCart) {
        List<ProductDTO> productDTOs = shoppingCart.getProducts().stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());

        return new ShoppingCartDTO(
                shoppingCart.getId(),
                productDTOs,
                shoppingCart.getUserId(),
                shoppingCart.getTotalValue()
        );
    }

    public static ShoppingCart toEntity(ShoppingCartDTO shoppingCartDTO) {
        List<Product> products = shoppingCartDTO.getProducts().stream()
                .map(ProductMapper::toEntity)
                .collect(Collectors.toList());

        return new ShoppingCart(
                shoppingCartDTO.getId(),
                products,
                shoppingCartDTO.getUserId(),
                shoppingCartDTO.getTotalValue()
        );
    }
}
