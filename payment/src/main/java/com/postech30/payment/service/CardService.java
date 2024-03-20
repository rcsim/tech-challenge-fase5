package com.postech30.payment.service;


import com.postech30.payment.dto.CardDTO;
import com.postech30.payment.exception.CardNotFoundException;

import java.util.List;

public interface CardService {

    List<CardDTO> getAllCards();
    CardDTO getCardById(Long id) throws CardNotFoundException;

    CardDTO createCard(CardDTO cardDTO);
    void deleteCard(Long id);

    CardDTO updatePayments(Long id, CardDTO cardDTO);
}
