package com.company.command;

import com.company.Account;
import com.company.Inventory;
import com.company.item.SodaItem;

public class OrderSmsSoda extends Command<SodaItem> {
    public OrderSmsSoda(Inventory inventory, Account account, SodaItem sodaItem) {
        super("sms order", inventory, account, sodaItem);
    }

    @Override
    public void execute() {
        SodaItem sodaItem = getParam();
        long itemCount = inventory.getItemCount(sodaItem);
        // This is a slight deviation from the original implementation as the original SodaMachine implementation
        // do not check whether we have the soda in the inventory at all.
        if (itemCount == 0) {
            System.out.println("No " + sodaItem.name().toLowerCase() + " left");
            return;
        }

        System.out.println("Giving " + sodaItem.name().toLowerCase() + " out");
        inventory.takeItem(sodaItem);
    }
}
