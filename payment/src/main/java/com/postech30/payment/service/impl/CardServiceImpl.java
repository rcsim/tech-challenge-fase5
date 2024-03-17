package com.postech30.payment.service.impl;

import com.postech30.payment.dto.CardDTO;
import com.postech30.payment.entity.Card;
import com.postech30.payment.exception.CardNotFoundException;
import com.postech30.payment.repository.CardRepository;
import com.postech30.payment.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardServiceImpl implements CardService {

        @Autowired
        private CardRepository cardRepository;

        public List<CardDTO> getAllCards() {
            return cardRepository.findAll().stream().map(CardDTO::new).collect(Collectors.toList());
        }

        public CardDTO getCardById(Long id) throws CardNotFoundException {
           Card card =  cardRepository.findById(id).orElseThrow(() -> new CardNotFoundException("Cartão não encontrado"));;
            return new CardDTO(card);
        }

        public CardDTO createCard(CardDTO cardDTO) {

            Card card =  cardRepository.save(new Card(cardDTO));
            return new CardDTO(card);
        }

        public void deleteCard(Long id) {
            cardRepository.deleteById(id);
        }
}
