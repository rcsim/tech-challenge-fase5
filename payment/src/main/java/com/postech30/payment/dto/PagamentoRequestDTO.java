package com.postech30.payment.dto;

import com.postech30.payment.enums.FormaPagamento;
import com.postech30.payment.enums.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PagamentoRequestDTO {

    private Long usuerId;
    private PaymentMethod paymentMethod;
    private Long cartID;
    private BigDecimal totalValue;
}
