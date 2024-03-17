package com.postech30.payment.service.impl;

import com.postech30.payment.dto.CardDTO;
import com.postech30.payment.dto.PaymentDTO;
import com.postech30.payment.dto.ShoppingCartDTO;
import com.postech30.payment.entity.Payment;
import com.postech30.payment.repository.PaymentRepository;
import com.postech30.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public PaymentDTO checkout(ShoppingCartDTO carrinho, CardDTO cardDTO) {


        Payment payment = new Payment(carrinho,cardDTO);

        if(has95PercentChanceOfPayment())
            payment.setStatus("Aprovado");
        else
            payment.setStatus("Recusado");


        return null;
    }

    private boolean  has95PercentChanceOfPayment(){
        Random random = new Random();
        return random.nextDouble() < 0.95;
    }
}
