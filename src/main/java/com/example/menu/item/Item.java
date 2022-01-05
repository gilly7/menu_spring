package com.example.menu.item;

// ✨ New import ✨
import org.springframework.data.annotation.Id;

public class Item {
    private final Long id;
    private final String name;
    private final Long price;
    private final String description;
    private final String image;

    public Item(
            Long id,
            String name,
            Long price,
            String description,
            String image
    ) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
    }

    @Id // ✨ New annotation ✨
    public Long getId() {
        return id;
    }

    // Other getter and class methods...
}
