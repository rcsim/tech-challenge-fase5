package com.postech30.msshoppingcart.service.impl;

import com.postech30.msshoppingcart.dto.ItemDTO;
import com.postech30.msshoppingcart.dto.ProductDTO;
import com.postech30.msshoppingcart.service.ProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Value("${endpoint.items}")
    private String endpoint;
    @Override
    public List<ProductDTO> getProductsDetails(List<ProductDTO> products) {
        RestTemplate rest = new RestTemplate();

        List<Long> productIds = new ArrayList<>();
        for (ProductDTO product : products) {
            productIds.add(product.getProductId());
        }

        ResponseEntity<List<ItemDTO>> response = rest.exchange(
                endpoint + "/itemsByIds",
                HttpMethod.POST,
                new HttpEntity<>(productIds),
                new ParameterizedTypeReference<List<ItemDTO>>() {}
        );

        List<ItemDTO> itemDTOS = response.getBody();

        if(itemDTOS != null){
            for (ProductDTO product : products) {
                for (ItemDTO itemDTO : itemDTOS) {
                    if (product.getProductId().equals((long) itemDTO.getId())) {
                        product.setName(itemDTO.getName());
                        product.setPrice(BigDecimal.valueOf(itemDTO.getAmount()));
                    }
                }
            }
        }

        return products;
    }

}
