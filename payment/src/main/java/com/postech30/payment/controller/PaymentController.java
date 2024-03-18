package com.postech30.payment.controller;

import com.postech30.payment.dto.CardDTO;
import com.postech30.payment.dto.PagamentoRequestDTO;
import com.postech30.payment.dto.PaymentDTO;
import com.postech30.payment.dto.ShoppingCartDTO;
import com.postech30.payment.service.CardService;
import com.postech30.payment.service.CartService;
import com.postech30.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {
   @Autowired
   private CartService cartService;

    @Autowired
    private CardService cardService;

    @Autowired
    private PaymentService paymentService;

@PostMapping("/chekout")
    public ResponseEntity<PaymentDTO> checkout(@RequestBody PagamentoRequestDTO request){
    ShoppingCartDTO carrinho = cartService.getCart(request.getCartID());
    CardDTO cardDTO = cardService.getCardById(request.getCardId());

    var payment = paymentService.checkout(carrinho,cardDTO);
    return ResponseEntity.ok().body(payment);
    }
}
