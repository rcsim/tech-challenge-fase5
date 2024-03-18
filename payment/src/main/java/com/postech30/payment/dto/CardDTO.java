package com.postech30.payment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.postech30.payment.entity.Card;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardDTO {
    @JsonProperty("id")
    public long id;
    @JsonProperty("numero_Card")
    private String cardNumber;
    @JsonProperty("titular_Card")
    private String cardHolderName;
    @JsonProperty("data_expiracao")
    private String expirationDate;
    @JsonProperty("codigo_seguranca")
    private String cvv;
    @JsonProperty("userId")
    private String userId;


    public CardDTO(Card Card){
        this.id = Card.getId();
        this.cardNumber = Card.getCardNumber();
        this.cvv = Card.getCvv();
        this.expirationDate = Card.getExpirationDate();
        this.userId = String.valueOf(Card.getUserId());
        this.cardHolderName = Card.getCardHolderName();
    }
}
