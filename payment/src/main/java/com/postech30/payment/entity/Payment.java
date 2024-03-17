package com.postech30.payment.entity;


import com.postech30.payment.dto.CardDTO;
import com.postech30.payment.dto.ShoppingCartDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_pagamento")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "valor")
    public BigDecimal valor;

    @Column(name = "status")
    private String status;

    @JoinColumn(name = "card_id")
    private long cardId;

    private long cartId;


    public Payment(ShoppingCartDTO carrinho, CardDTO cardDTO) {
        this.valor = carrinho.getTotalValue();
        this.cartId = carrinho.getId();
        this.cartId = cardDTO.getId();
    }
}
