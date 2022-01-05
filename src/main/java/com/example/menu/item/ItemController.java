package com.example.menu.item;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/menu/items")
public class ItemController {
    private final ItemService service;

    public ItemController(ItemService service) {...}

    @GetMapping
    public ResponseEntity<List<Item>> findAll() {...}

    @GetMapping("/{id}")
    public ResponseEntity<Item> find(@PathVariable("id") Long id) {...}

    @PostMapping
    public ResponseEntity<Item> create(@RequestBody Item item) {...}

    // ✨ New! 👇 PUT definition ✨
    @PutMapping("/{id}")
    public ResponseEntity<Item> update(
            @PathVariable("id") Long id,
            @RequestBody Item updatedItem) {

        Optional<Item> updated = service.update(id, updatedItem);

        return updated
                .map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> {
                    Item created = service.create(updatedItem);
                    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(created.getId())
                            .toUri();
                    return ResponseEntity.created(location).body(created);
                });
    }

    // ✨ New! 👇 DELETE definition ✨
    @DeleteMapping("/{id}")
    public ResponseEntity<Item> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}