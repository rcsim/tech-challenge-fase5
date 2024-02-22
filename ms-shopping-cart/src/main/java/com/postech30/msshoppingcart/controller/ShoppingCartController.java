package com.postech30.msshoppingcart.controller;

import com.postech30.msshoppingcart.dto.ShoppingCartDTO;
import com.postech30.msshoppingcart.service.ShoppingCartService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/shopping-cart")
@Validated
@Transactional
public class ShoppingCartController {

    @Autowired
    ShoppingCartService shoppingCartService;

    @PostMapping
    public ResponseEntity<ShoppingCartDTO> saveShoppingCart(@RequestBody @Valid ShoppingCartDTO shoppingCartDTO) {
        var shoppingCartSave = shoppingCartService.createShoppingCart(shoppingCartDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(shoppingCartSave);
    }

    @GetMapping ResponseEntity<Page<ShoppingCartDTO>> getShoppingCarts(
            @RequestParam(defaultValue = "") String search, Pageable pageable)  {
        Page<ShoppingCartDTO> page = shoppingCartService.getShoppingCarts(search, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCartDTO> getShoppingCartById(@PathVariable Long id) {
        var shoppingCart = shoppingCartService.getShoppingCartById(id);
        return ResponseEntity.status(HttpStatus.OK).body(shoppingCart);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<String> updateShoppingCart(@PathVariable Long id, @RequestBody @Valid ShoppingCartDTO shoppingCartDTO) {
        shoppingCartService.updateShoppingCart(id, shoppingCartDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Shopping cart updated!!");
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<String> deleteShoppingCart(@PathVariable Long id) {
        shoppingCartService.deleteShoppingCart(id);
        return ResponseEntity.status(HttpStatus.OK).body("Shopping cart deleted!!");
    }
}
