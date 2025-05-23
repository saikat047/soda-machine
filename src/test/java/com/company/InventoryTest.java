package com.company;

import com.company.item.SodaItem; // Your SodaItem class/enum
import com.company.item.SodaItemFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.company.item.SodaItemType.COKE;
import static com.company.item.SodaItemType.FANTA;
import static com.company.item.SodaItemType.SPRITE;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InventoryTest {

    private Inventory inventory;
    private SodaItem cokeItem;
    private SodaItem spriteItem;
    private SodaItem fantaItem;

    @BeforeEach
    void setUp() {
        inventory = new Inventory();
        SodaItemFactory factory = new SodaItemFactory();
        cokeItem = factory.createItem(COKE);
        spriteItem = factory.createItem(SPRITE);
        fantaItem = factory.createItem(FANTA);
    }

    @Test
    void getItemCount_shouldReturnZero() {
        assertEquals(0L, inventory.getItemCount(cokeItem));
        assertEquals(0L, inventory.getItemCount(spriteItem));
        assertEquals(0L, inventory.getItemCount(fantaItem));
    }

    @Test
    void addItem_andGetItemCount_shouldReflectAddition() {
        inventory.addItem(cokeItem, 5);
        assertEquals(5L, inventory.getItemCount(cokeItem));

        // Add more of the same item
        inventory.addItem(cokeItem, 3);
        assertEquals(8L, inventory.getItemCount(cokeItem));
    }

    @Test
    @DisplayName("Take item should decrease count, and throw IllegalStateException if item not available")
    void takeItem_shouldDecreaseCountOrThrowException() {
        inventory.addItem(spriteItem, 2); // Start with 2 SPRITEs
        assertEquals(2L, inventory.getItemCount(spriteItem));

        assertDoesNotThrow(() -> inventory.takeItem(spriteItem));
        assertEquals(1L, inventory.getItemCount(spriteItem));

        assertDoesNotThrow(() -> inventory.takeItem(spriteItem));
        assertEquals(0L, inventory.getItemCount(spriteItem));

        IllegalStateException exceptionZeroCount = assertThrows(IllegalStateException.class, () -> {
            inventory.takeItem(spriteItem);
        });
        assertTrue(exceptionZeroCount.getMessage().contains("No available soda item"));

        // Attempt to take an item never added
        IllegalStateException exceptionNeverAdded = assertThrows(IllegalStateException.class, () -> {
            inventory.takeItem(fantaItem);
        });
        assertTrue(exceptionNeverAdded.getMessage().contains("No available soda item"));
    }

    @Test
    @DisplayName("addItem: Adding zero items should not change the count")
    void addItem_withZeroQuantity_shouldNotChangeCount() {
        inventory.addItem(cokeItem, 3);
        assertEquals(3L, inventory.getItemCount(cokeItem));

        inventory.addItem(cokeItem, 0);
        assertEquals(3L, inventory.getItemCount(cokeItem));
    }

    @Test
    void addItem_withNegativeQuantity_shouldThrowException() {
        inventory.addItem(cokeItem, 5);
        assertThrows(IllegalArgumentException.class, () -> {
            inventory.addItem(cokeItem, -1);
        });
    }
}