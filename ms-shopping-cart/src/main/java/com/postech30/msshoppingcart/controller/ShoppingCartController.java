package com.postech30.msshoppingcart.controller;

import com.postech30.msshoppingcart.dto.ProductDTO;
import com.postech30.msshoppingcart.dto.ShoppingCartDTO;
import com.postech30.msshoppingcart.service.ProductService;
import com.postech30.msshoppingcart.service.ShoppingCartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/shopping-cart")
@Validated
@Transactional
public class ShoppingCartController {

    ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping
    @Operation(summary = "Salvar carrinho de compras.",
            description = "Adiciona um carrinho de compras na base de dados do sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Carrinho de compras salvo.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ShoppingCartDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Requisição inválida.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Não autorizado.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Acesso proibido.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.", content = {@Content(mediaType = "application/json")})
    })
    public ResponseEntity<ShoppingCartDTO> saveShoppingCart(@RequestBody @Valid ShoppingCartDTO shoppingCartDTO) {
        var shoppingCartSave = shoppingCartService.createShoppingCart(shoppingCartDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(shoppingCartSave);
    }

    @GetMapping
    @Operation(summary = "Listar todos os carrinhos de compras.",
        description = "Lista todos os carrinhos de compras na base de dados do sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem-sucedida.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ShoppingCartDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Requisição inválida.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Não autorizado.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Acesso proibido.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.", content = {@Content(mediaType = "application/json")})
    })
    ResponseEntity<Page<ShoppingCartDTO>> getShoppingCarts(@RequestParam(defaultValue = "") String search, Pageable pageable) {
        Page<ShoppingCartDTO> page = shoppingCartService.getShoppingCarts(search, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter carrinho de compras por ID.",
            description = "Obtém um carrinho de compras na base de dados do sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem-sucedida.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ShoppingCartDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Requisição inválida.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Não autorizado.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Acesso proibido.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.", content = {@Content(mediaType = "application/json")})
    })
    public ResponseEntity<ShoppingCartDTO> getShoppingCartById(@PathVariable Long id) {
        var shoppingCart = shoppingCartService.getShoppingCartById(id);
        return ResponseEntity.status(HttpStatus.OK).body(shoppingCart);
    }

    @PutMapping(value = "{id}")
    @Operation(summary = "Atualizar carrinho de compras.",
            description = "Atualiza um carrinho de compras na base de dados do sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem-sucedida.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ShoppingCartDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Requisição inválida.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Não autorizado.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Acesso proibido.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.", content = {@Content(mediaType = "application/json")})
    })
    public ResponseEntity<String> updateShoppingCart(@PathVariable Long id, @RequestBody @Valid ShoppingCartDTO shoppingCartDTO) {
        shoppingCartService.updateShoppingCart(id, shoppingCartDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Shopping cart updated!!");
    }

    @DeleteMapping(value = "{id}")
    @Operation(summary = "Deletar carrinho de compras.",
            description = "Deleta um carrinho de compras na base de dados do sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem-sucedida.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ShoppingCartDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Requisição inválida.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Não autorizado.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Acesso proibido.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.", content = {@Content(mediaType = "application/json")})
    })
    public ResponseEntity<String> deleteShoppingCart(@PathVariable Long id) {
        shoppingCartService.deleteShoppingCart(id);
        return ResponseEntity.status(HttpStatus.OK).body("Shopping cart deleted!!");
    }

    @PutMapping(value = "{id}/products")
    @Operation(summary = "Adicionar produtos ao carrinho de compras.",
            description = "Adiciona produtos ao carrinho de compras na base de dados do sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem-sucedida.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ShoppingCartDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Requisição inválida.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Não autorizado.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Acesso proibido.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.", content = {@Content(mediaType = "application/json")})
    })
    public ResponseEntity<ShoppingCartDTO> addProductsToShoppingCart(@PathVariable Long id, @RequestBody List<ProductDTO> products) {
        var shoppingCart = shoppingCartService.addProductsToShoppingCart(id, products);
        return ResponseEntity.status(HttpStatus.OK).body(shoppingCart);
    }

    @PutMapping(value = "{id}/remove")
    @Operation(summary = "Remover um produto do carrinho de compras.",
        description = "Remove um produto do carrinho de compras na base de dados do sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem-sucedida.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ShoppingCartDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Requisição inválida.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Não autorizado.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Acesso proibido.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.", content = {@Content(mediaType = "application/json")})
    })
    public ResponseEntity<ShoppingCartDTO> removeProductsFromShoppingCart(@PathVariable Long id, @RequestBody List<Long> productIds) {
        var shoppingCart = shoppingCartService.removeProductFromShoppingCart(id, productIds);
        return ResponseEntity.status(HttpStatus.OK).body(shoppingCart);
    }

    @PutMapping(value = "{id}/clear")
    @Operation(summary = "Limpar carrinho de compras.",
        description = "Limpa o carrinho de compras na base de dados do sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem-sucedida.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ShoppingCartDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Requisição inválida.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Não autorizado.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Acesso proibido.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.", content = {@Content(mediaType = "application/json")})
    })
    public ResponseEntity<ShoppingCartDTO> clearShoppingCart(@PathVariable Long id) {
        var shoppingCart = shoppingCartService.clearShoppingCart(id);
        return ResponseEntity.status(HttpStatus.OK).body(shoppingCart);
    }

}
