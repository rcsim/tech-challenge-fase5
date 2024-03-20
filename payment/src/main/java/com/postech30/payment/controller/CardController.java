package com.postech30.payment.controller;

import com.postech30.payment.dto.CardDTO;
import com.postech30.payment.entity.Card;
import com.postech30.payment.exception.CardNotFoundException;
import com.postech30.payment.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {

        @Autowired
        private CardService cardService;

    @Operation(summary = "Salvar um cartão",
            description = "Salvar um cartão na base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cartão cadastrado"),
            @ApiResponse(responseCode = "400", description = "informação de entrada incorreta")
    })
    @PostMapping
    public ResponseEntity<CardDTO> createCard(@RequestBody CardDTO cardDTO) {
        CardDTO createdCard = cardService.createCard(cardDTO);
        return new ResponseEntity<>(createdCard, HttpStatus.CREATED);
    }

    @Operation(summary = "Busca todos os cartões",
            description = "Busca todos os cartões na base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cartões encontrados"),
            @ApiResponse(responseCode = "400", description = "Nenhum cartão encontrado")
    })
        @GetMapping
        public ResponseEntity<List<CardDTO>> getAllCards() {
            return ResponseEntity.ok().body(cardService.getAllCards());
        }

    @Operation(summary = "Busca  cartão por id",
            description = "Busca  cartão por id na base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cartão encontrado"),
            @ApiResponse(responseCode = "400", description = "Nenhum cartão encontrado")
    })
        @GetMapping("/{id}")
        public ResponseEntity<CardDTO> getCardById(@PathVariable Long id) throws CardNotFoundException {
           var Card =  cardService.getCardById(id);
            return ResponseEntity.ok().body(Card);
        }

    @Operation(summary = "deletar um cartão",
            description = "deletar cartão na base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cartão deletado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Nenhum cartão encontrado")
    })

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteCard(@PathVariable Long id) {
            cardService.deleteCard(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

    @Operation(summary = "Atualizar dados cartão",
            description = "Atualizar um cartão na base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cartão Atualizado"),
            @ApiResponse(responseCode = "400", description = "informação de entrada incorreta")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CardDTO> updateCard(@PathVariable Long id,@RequestBody CardDTO cardDTO){
       CardDTO response= cardService.updatePayments(id,cardDTO);
        return ResponseEntity.ok().body(response);
    }

}
