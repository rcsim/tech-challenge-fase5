package com.postech30.msshoppingcart.mapper;

import com.postech30.msshoppingcart.dto.ProductDTO;
import com.postech30.msshoppingcart.entity.Product;

public class ProductMapper {
    public static ProductDTO toDTO(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getProductId(),
                product.getName(),
                product.getPrice(),
                product.getQuantity()
        );
    }

    public static Product toEntity(ProductDTO productDTO) {
        return new Product(
                productDTO.getProductId(),
                productDTO.getName(),
                productDTO.getPrice(),
                productDTO.getQuantity()
        );
    }
}
