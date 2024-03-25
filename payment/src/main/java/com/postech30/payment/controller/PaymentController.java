package com.postech30.payment.controller;

import com.postech30.payment.dto.CardDTO;
import com.postech30.payment.dto.PagamentoRequestDTO;
import com.postech30.payment.dto.PaymentDTO;
import com.postech30.payment.dto.ShoppingCartDTO;
import com.postech30.payment.service.CardService;
import com.postech30.payment.service.CartService;
import com.postech30.payment.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/payment")
public class PaymentController {


    private final CartService cartService;

    private final CardService cardService;

    private final PaymentService paymentService;

    public PaymentController(CartService cartService, CardService cardService, PaymentService paymentService) {
        this.cartService = cartService;
        this.cardService = cardService;
        this.paymentService = paymentService;
    }

    @PostMapping("/checkout")
    @Operation(summary = "Finalizar compra.",
            description = "Finaliza a compra de um carrinho de compras.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem-sucedida.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PaymentDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Requisição inválida.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Não autorizado.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Acesso proibido.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.", content = {@Content(mediaType = "application/json")})
    })
    public ResponseEntity<PaymentDTO> checkout(@RequestBody @Valid PagamentoRequestDTO request) {
        ShoppingCartDTO carrinho = cartService.getCart(request.getCartID());
        CardDTO cardDTO = cardService.getCardById(request.getCardId());

        var payment = paymentService.checkout(carrinho, cardDTO);
        return ResponseEntity.ok().body(payment);
    }
}
