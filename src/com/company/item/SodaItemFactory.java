package com.company.item;

// The purpose of the Factory is to allow internationalization of the soda labels.
// The factory can also be developed to allow the prices to be changed over time due to business decisions.
public class SodaItemFactory {

    public SodaItem createItem(SodaItemType type) {
        return switch (type) {
            case COKE -> new SodaItem(SodaItemType.COKE, "Coke", 20);
            case SPRITE -> new SodaItem(SodaItemType.SPRITE, "Sprite", 15);
            case FANTA -> new SodaItem(SodaItemType.FANTA, "Fanta", 15);
        };
    }
}
