package com.postech30.msshoppingcart.service.impl;

import com.postech30.msshoppingcart.dto.ShoppingCartDTO;
import com.postech30.msshoppingcart.entity.ShoppingCart;
import com.postech30.msshoppingcart.mapper.ShoppingCartMapper;
import com.postech30.msshoppingcart.repository.ShoppingCartRepository;
import com.postech30.msshoppingcart.service.ShoppingCartService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@AllArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;

    @Override
    public ShoppingCartDTO createShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = ShoppingCartMapper.toEntity(shoppingCartDTO);
        ShoppingCart savedShoppingCart = shoppingCartRepository.save(shoppingCart);
        return ShoppingCartMapper.toDTO(savedShoppingCart);
    }

    @Override
    public Page<ShoppingCartDTO> getShoppingCarts(String search, Pageable pageable) {
        // Implement search logic here
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
            // Update shoppingCart fields here
            shoppingCartRepository.save(shoppingCart);
        }
    }

    @Override
    public void deleteShoppingCart(Long id) {
        shoppingCartRepository.deleteById(id);
    }
}
