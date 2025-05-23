package com.company.item;

import com.company.util.Utils;

import java.util.Arrays;
import java.util.Optional;

public enum SodaItemType {
    COKE("coke"), SPRITE("sprite"), FANTA("fanta");

    private final String typeName;

    SodaItemType(String typeName) {
        this.typeName = typeName;
    }

    public static Optional<SodaItemType> findType(String typeName) {
        Utils.verifyNotEmpty(typeName);
        return Arrays.stream(values())
                .filter(item -> item.typeName.equals(typeName))
                .findFirst();
    }
}
