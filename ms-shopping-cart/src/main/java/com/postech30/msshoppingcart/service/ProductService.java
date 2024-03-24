package com.postech30.msshoppingcart.service;

import com.postech30.msshoppingcart.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    public List<ProductDTO> getProductsDetails(List<ProductDTO> products);
}
