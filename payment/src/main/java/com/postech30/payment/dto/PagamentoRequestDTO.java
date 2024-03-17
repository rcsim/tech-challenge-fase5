package com.postech30.payment.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PagamentoRequestDTO {

    private Long cardId;
    private Long cartID;

}
