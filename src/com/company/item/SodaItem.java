package com.company.item;

import com.company.util.Utils;

import java.util.Objects;

public record SodaItem (SodaItemType type, String name, int price) {
    public SodaItem {
        Objects.requireNonNull(type);
        if (Utils.isEmpty(name)) {
            throw new IllegalArgumentException("Soda item name cannot be empty");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Soda item price must be a positive number");
        }
    }
}
