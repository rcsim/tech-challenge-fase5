package com.postech30.msitems.controller;

import com.postech30.msitems.model.Item;
import com.postech30.msitems.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Validated
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    @Operation(summary = "Listar todos os itens.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem-sucedida.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Item.class))}),
            @ApiResponse(responseCode = "201", description = "Recurso criado com sucesso.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Requisição inválida.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Não autorizado.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Acesso proibido.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.", content = {@Content(mediaType = "application/json")})
    })
    public ResponseEntity<Page<Item>> listAllItems(@PageableDefault(size = 5) Pageable pageable) {
        return itemService.listAllItems(pageable);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Listar todos os itens cadastrados pelo usuário.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem-sucedida.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Item.class))}),
            @ApiResponse(responseCode = "201", description = "Recurso criado com sucesso.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Requisição inválida.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Não autorizado.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Acesso proibido.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.", content = {@Content(mediaType = "application/json")})
    })
    public ResponseEntity<Page<Item>> listUserItems(@PathVariable int userId, @PageableDefault(size = 5) Pageable pageable) {
        return itemService.listUserItems(userId, pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Listar item cadastrados pelo Id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem-sucedida.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Item.class))}),
            @ApiResponse(responseCode = "201", description = "Recurso criado com sucesso.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Requisição inválida.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Não autorizado.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Acesso proibido.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.", content = {@Content(mediaType = "application/json")})
    })
    public ResponseEntity<?> listById(@PathVariable int id) {
        return itemService.listById(id);
    }

    @PostMapping("/itemsByIds")
    @Operation(summary = "Listar itens por uma lista de IDs.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem-sucedida.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Item.class))}),
            @ApiResponse(responseCode = "400", description = "Requisição inválida.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Não autorizado.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Acesso proibido.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.", content = {@Content(mediaType = "application/json")})
    })
    public ResponseEntity<?> getItemsByIds(@RequestBody List<Integer> ids, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return itemService.getItemsByIds(ids, pageable);
    }

    @PostMapping
    @Operation(summary = "Adicionar um item.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem-sucedida.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Item.class))}),
            @ApiResponse(responseCode = "201", description = "Recurso criado com sucesso.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Requisição inválida.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Não autorizado.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Acesso proibido.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.", content = {@Content(mediaType = "application/json")})
    })
    public ResponseEntity<?> addItem(@Valid @RequestBody Item item) {
        return itemService.addItem(item);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um item.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem-sucedida.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Item.class))}),
            @ApiResponse(responseCode = "201", description = "Recurso criado com sucesso.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Requisição inválida.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Não autorizado.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Acesso proibido.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.", content = {@Content(mediaType = "application/json")})
    })
    public ResponseEntity<?> updateItem(@PathVariable int id, @RequestBody @Valid Item item) {
        return itemService.updateItem(id, item);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir um item.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem-sucedida.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Item.class))}),
            @ApiResponse(responseCode = "201", description = "Recurso criado com sucesso.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Requisição inválida.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Não autorizado.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Acesso proibido.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.", content = {@Content(mediaType = "application/json")})
    })
    public ResponseEntity<?> deleteItem(@PathVariable int id) {
        return itemService.deleteItem(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
