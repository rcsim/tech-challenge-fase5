package com.postech30.payment.controller;

import com.postech30.payment.dto.CartDTO;
import com.postech30.payment.dto.PagamentoRequestDTO;
import com.postech30.payment.dto.PaymentDTO;
import com.postech30.payment.dto.UserDTO;
import com.postech30.payment.enums.PaymentMethod;
import com.postech30.payment.service.CartService;
import com.postech30.payment.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {


    private UserService userService;

    private CartService cartService;

@PostMapping("/chekout")
    public ResponseEntity<PaymentDTO> checkout(@RequestBody PagamentoRequestDTO request){
    //Esperar objeto do user
    UserDTO usuario = userService.getUser(request.getUsuerId());
    // Esperar objeto do cart
   CartDTO carrinho = cartService.getCart(request.getCartID());

    PaymentDTO payment = new PaymentDTO();

    if (request.getPaymentMethod() == PaymentMethod.CARTAO_CREDITO) {
        // Chamar API de pagamento
    } else if (request.getPaymentMethod() == PaymentMethod.PIX) {
        // Chamar API de pagamento
    }

    return ResponseEntity.ok().body(payment);

    }
}
