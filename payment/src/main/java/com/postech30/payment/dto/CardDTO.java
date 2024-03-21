package com.postech30.payment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.postech30.payment.entity.Card;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank
    @JsonProperty("numero_Card")
    private String cardNumber;
    @NotBlank
    @JsonProperty("titular_Card")
    private String cardHolderName;
    @NotBlank
    @JsonProperty("data_expiracao")
    private String expirationDate;
    @NotBlank
    @JsonProperty("codigo_seguranca")
    private String cvv;
    @NotBlank
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
