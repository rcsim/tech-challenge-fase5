package com.postech30.msshoppingcart.repository;

import com.postech30.msshoppingcart.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
