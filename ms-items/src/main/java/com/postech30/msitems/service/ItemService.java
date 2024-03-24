package com.postech30.msitems.service;

import com.postech30.msitems.model.Item;
import com.postech30.msitems.repository.ItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

    public ResponseEntity<Page<Item>> listAllItems(Pageable pageable) {
        Page<Item> items = itemRepository.findAll(pageable);
        if (!items.isEmpty()) {
            return ResponseEntity.ok().body(items);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Page.empty());
        }
    }

    public ResponseEntity<Page<Item>> listUserItems(int userId, Pageable pageable) {
        Page<Item> items = itemRepository.findByUserId(userId, pageable);
        if (!items.isEmpty()) {
            return ResponseEntity.ok().body(items);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Page.empty());
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

    public ResponseEntity<List<Item>> getItemsByIds(List<Integer> ids) {
        List<Item> items = itemRepository.findAllById(ids);
        if (!items.isEmpty()) {
            return ResponseEntity.ok().body(items);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(items);
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
