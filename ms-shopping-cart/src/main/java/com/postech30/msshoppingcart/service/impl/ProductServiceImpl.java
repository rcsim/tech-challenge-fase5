package com.postech30.msshoppingcart.service.impl;

import com.postech30.msshoppingcart.dto.ProductDTO;
import com.postech30.msshoppingcart.entity.Product;
import com.postech30.msshoppingcart.mapper.ProductMapper;
import com.postech30.msshoppingcart.repository.ProductRepository;
import com.postech30.msshoppingcart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Value("${endpoint.items}")
    private String endpoint;
    @Override
    public List<ProductDTO> getProducts(List<Long> productIds) {
        RestTemplate rest = new RestTemplate();
        List<ProductDTO> products = new ArrayList<>();

        for(Long id : productIds) {
            var response = rest.getForEntity(endpoint + "/" + id, ProductDTO.class);
            products.add(response.getBody());
        }

        return products;
    }

    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {
        Product product = productRepository.save(ProductMapper.toEntity(productDTO));
        return ProductMapper.toDTO(product);
    }





}
