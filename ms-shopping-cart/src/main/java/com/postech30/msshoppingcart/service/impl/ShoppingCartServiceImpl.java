package com.postech30.msshoppingcart.service.impl;

import com.postech30.msshoppingcart.dto.ProductDTO;
import com.postech30.msshoppingcart.dto.ShoppingCartDTO;
import com.postech30.msshoppingcart.entity.Product;
import com.postech30.msshoppingcart.entity.ShoppingCart;
import com.postech30.msshoppingcart.exceptions.BadRequestException;
import com.postech30.msshoppingcart.mapper.ProductMapper;
import com.postech30.msshoppingcart.mapper.ShoppingCartMapper;
import com.postech30.msshoppingcart.repository.ShoppingCartRepository;
import com.postech30.msshoppingcart.service.ProductService;
import com.postech30.msshoppingcart.service.ShoppingCartService;
import com.postech30.msshoppingcart.utils.ShoppingCartUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Collections.addAll;

@Service
@AllArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductService productService;

    @Override
    public ShoppingCartDTO createShoppingCart(ShoppingCartDTO shoppingCartDTO) {

        List<ProductDTO> products = productService.getProductsDetails(shoppingCartDTO.getProducts());
        shoppingCartDTO.setProducts(products);

        ShoppingCart existingShoppingCart = shoppingCartRepository.findByUserId(shoppingCartDTO.getUserId());

        if (existingShoppingCart != null) {
            throw new BadRequestException("Shopping cart already exists for user with id: " + shoppingCartDTO.getUserId());
        }

        shoppingCartDTO.setTotalValue(ShoppingCartUtils.CalculateTotalValue(shoppingCartDTO));

        ShoppingCart shoppingCart = ShoppingCartMapper.toEntity(shoppingCartDTO);
        ShoppingCart savedShoppingCart = shoppingCartRepository.save(shoppingCart);
        return ShoppingCartMapper.toDTO(savedShoppingCart);
    }

    @Override
    public Page<ShoppingCartDTO> getShoppingCarts(String search, Pageable pageable) {
        Page<ShoppingCart> shoppingCarts = shoppingCartRepository.findAll(pageable);
        return shoppingCarts.map(ShoppingCartMapper::toDTO);
    }

    @Override
    public ShoppingCartDTO getShoppingCartById(Long id) {
        Optional<ShoppingCart> shoppingCart = shoppingCartRepository.findById(id);
        return shoppingCart.map(ShoppingCartMapper::toDTO).orElse(null);
    }

    @Override
    public void updateShoppingCart(Long id, ShoppingCartDTO shoppingCartDTO) {
        Optional<ShoppingCart> shoppingCartOptional = shoppingCartRepository.findById(id);
        if (shoppingCartOptional.isPresent()) {
            ShoppingCart shoppingCart = shoppingCartOptional.get();
            shoppingCartRepository.save(shoppingCart);
        }
    }

    @Override
    public void deleteShoppingCart(Long id) {
        shoppingCartRepository.deleteById(id);
    }

    @Override
    public ShoppingCartDTO addProductsToShoppingCart(Long id, List<ProductDTO> products) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Shopping cart with id: " + id + " not found"));

        List<Product> currentProducts = shoppingCart.getProducts() == null ? new ArrayList<>() : shoppingCart.getProducts();


        for (ProductDTO productDTO : products) {
            Optional<Product> productOptional = currentProducts.stream()
                    .filter(product -> product.getProductId() == productDTO.getProductId())
                    .findFirst();
            if (productOptional.isPresent()) {
                Product product = productOptional.get();
                product.setQuantity(product.getQuantity() + productDTO.getQuantity());
                currentProducts.removeIf(p -> p.getProductId() == product.getProductId());
                currentProducts.add(product);

            }
            else {
                currentProducts.add(ProductMapper.toEntity(productDTO));
            }
        }

        List<ProductDTO> currentProductsDTO = currentProducts.stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());

        //currentProductsDTO.addAll(products);

        currentProductsDTO = productService.getProductsDetails(currentProductsDTO);

        shoppingCart.setProducts(currentProductsDTO.stream()
                .map(ProductMapper::toEntity)
                .collect(Collectors.toList()));

        shoppingCart.setTotalValue(ShoppingCartUtils.CalculateTotalValue(ShoppingCartMapper.toDTO(shoppingCart)));

        shoppingCartRepository.save(shoppingCart);

        return ShoppingCartMapper.toDTO(shoppingCart);

    }

    @Override
    public ShoppingCartDTO clearShoppingCart(Long id) {
        Optional<ShoppingCart> shoppingCartOptional = shoppingCartRepository.findById(id);
        if (shoppingCartOptional.isPresent()) {
            ShoppingCart shoppingCart = shoppingCartOptional.get();
            List<Product> products = new ArrayList<>();
            shoppingCart.setProducts(products);
            shoppingCart.setTotalValue(BigDecimal.ZERO);

            shoppingCartRepository.save(shoppingCart);

            return ShoppingCartMapper.toDTO(shoppingCart);
        }
        else {
            throw new BadRequestException("Shopping cart with id: " + id + " not found");
        }
    }

    @Override
    public ShoppingCartDTO removeProductFromShoppingCart(Long id, List<Long> productIds) {
        Optional<ShoppingCart> shoppingCartOptional = shoppingCartRepository.findById(id);
        if (shoppingCartOptional.isPresent()) {
            ShoppingCart shoppingCart = shoppingCartOptional.get();
            List<Product> currentProducts = shoppingCart.getProducts();
            for (Long productId : productIds) {
                currentProducts.removeIf(product -> product.getProductId() == productId);
            }

            shoppingCart.setProducts(currentProducts);
            shoppingCart.setTotalValue(ShoppingCartUtils.CalculateTotalValue(ShoppingCartMapper.toDTO(shoppingCart)));

            shoppingCartRepository.save(shoppingCart);

            return ShoppingCartMapper.toDTO(shoppingCart);
        }
        else {
            throw new BadRequestException("Shopping cart with id: " + id + " not found");
        }
    }

}
