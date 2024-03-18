package com.postech30.payment.exception;

public class CardNotFoundException  extends RuntimeException  {
    public CardNotFoundException(String message){
        super(message);
    }

}
