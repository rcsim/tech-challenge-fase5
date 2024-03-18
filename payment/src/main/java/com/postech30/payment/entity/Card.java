package com.postech30.payment.entity;


import com.postech30.payment.dto.CardDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_cartao")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    @Column(name = "numero_cartao")
    private String cardNumber;
    @Column(name = "detentor_cartao")
    private String cardHolderName;
    @Column(name = "data_expiracao")
    private String expirationDate;
    @Column(name = "cvv")
    private String cvv;
    @JoinColumn(name = "user_id")
    private int userId;



    public Card(CardDTO cartaoDto) {
        this.cardNumber = cartaoDto.getCardNumber();
        this.cvv = cartaoDto.getCvv();
        this.expirationDate = cartaoDto.getExpirationDate();
        this.userId = Integer.parseInt(cartaoDto.getUserId());
        this.cardHolderName = cartaoDto.getCardHolderName();
    }
}
