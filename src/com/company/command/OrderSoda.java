package com.company.command;

import com.company.Account;
import com.company.Inventory;
import com.company.item.SodaItem;

import java.util.Objects;

public class OrderSoda extends Command<SodaItem> {
    public OrderSoda(Inventory inventory, Account account, SodaItem sodaItem) {
        super(inventory, account, Objects.requireNonNull(sodaItem));
    }

    @Override
    public void execute() {
        SodaItem sodaItem = getParam();
        long itemCount = inventory.getItemCount(sodaItem);
        if (itemCount == 0) {
            System.out.println("No " + sodaItem.name().toLowerCase() + " left");
            return;
        }

        if (account.getBalance() < sodaItem.price()) {
            long priceDiff = sodaItem.price() - account.getBalance();
            System.out.println("Need " + priceDiff + " more");
            return;
        }

        System.out.println("Giving " + sodaItem.name().toLowerCase() + " out");
        account.deductAmount(sodaItem.price());
        inventory.takeItem(sodaItem);
        System.out.println("Giving " + account.getBalance() + " out in change");
        account.reset();
    }
}
