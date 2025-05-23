package com.company;

import com.company.item.SodaItem;

import java.util.HashMap;
import java.util.Map;

// A soda machine is a synchronous vending machine and hence the Inventory does not need to be thread-safe.
public class Inventory {

    private final Map<SodaItem, Long> sodaItemCount = new HashMap<>();

    public void addItem(final SodaItem item, final int numOfItems) {
        Long itemCount = sodaItemCount.getOrDefault(item, 0L);
        itemCount += numOfItems;
        sodaItemCount.put(item, itemCount);
    }

    public void takeItem(final SodaItem item) {
        Long itemCount = sodaItemCount.getOrDefault(item, 0L);
        if (itemCount <= 0) {
            throw new IllegalStateException("No available soda item " + item);
        }
        --itemCount;
        sodaItemCount.put(item, itemCount);
    }

    public long getItemCount(final SodaItem item) {
        Long itemCount = sodaItemCount.get(item);
        return itemCount == null ? 0L : itemCount;
    }
}
