package com.postech30.msitems.service;

import com.postech30.msitems.model.Item;
import com.postech30.msitems.repository.ItemRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public ResponseEntity<?> listAllItems() {
        List<Item> items = itemRepository.findAll();
        if (!items.isEmpty()) {
            return ResponseEntity.ok().body(items);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existem itens cadastrados.");
        }
    }

    public ResponseEntity<?> listUserItems(int userId) {
        List<Item> items = itemRepository.findByUserId(userId);
        if (!items.isEmpty()) {
            return ResponseEntity.ok().body(items);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existem itens cadastrados para o usuário.");
        }
    }

    public ResponseEntity<?> listById(int id) {
        Item item = itemRepository.findById(id).orElse(null);

        if (item != null) {
            return ResponseEntity.ok().body(item);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item não encontrado.");
        }
    }

    public ResponseEntity<?> addItem(Item item) {
        Item newItem = itemRepository.save(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(newItem);
    }

    public ResponseEntity<?> updateItem(int id, Item item) {
        Item updatedItem = itemRepository.findById(id).map(i -> {
            i.setName(item.getName());
            i.setDescription(item.getDescription());
            i.setAmount(item.getAmount());
            i.setUserId(item.getUserId());
            return itemRepository.save(i);
        }).orElse(null);

        if (updatedItem != null) {
            return ResponseEntity.ok().body(updatedItem);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item não encontrado.");
        }
    }

    public ResponseEntity<?> deleteItem(int id) {
        if (itemRepository.existsById(id)) {
            itemRepository.deleteById(id);
            return ResponseEntity.ok().body("Item excluído com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item não encontrado.");
        }
    }
}
