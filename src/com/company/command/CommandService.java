package com.company.command;

import com.company.Account;
import com.company.Inventory;
import com.company.item.SodaItem;
import com.company.item.SodaItemFactory;
import com.company.item.SodaItemType;

import java.util.Objects;
import java.util.Optional;

public class CommandService {

    private final Inventory inventory;
    private final Account account;
    private final SodaItemFactory sodaItemFactory;

    public CommandService(Inventory inventory, Account account, SodaItemFactory sodaItemFactory) {
        this.inventory = inventory;
        this.account = account;
        this.sodaItemFactory = sodaItemFactory;
    }

    public Optional<Command<?>> createCommand(final String command) {
        Objects.requireNonNull(command);

        if (command.startsWith("insert")) {
            long money = Integer.parseInt(command.split(" ")[1]);
            return Optional.of(new InsertMoney(inventory, account, money));
        }
        else if (command.startsWith("order")) {
            // split string on space
            var sodaTypeName = command.split(" ")[1];
            Optional<SodaItemType> sodaType = SodaItemType.findType(sodaTypeName);
            if (sodaType.isEmpty()) {
                System.out.println("No such soda");
            }

            return sodaType.map(type -> {
                SodaItem sodaItem = sodaItemFactory.createItem(type);
                return new OrderSoda(inventory, account, sodaItem);
            });
        }
        else if (command.startsWith("sms order")) {
            var sodaTypeName = command.split(" ")[2];
            Optional<SodaItemType> sodaType = SodaItemType.findType(sodaTypeName);
            if (sodaType.isEmpty()) {
                System.out.println("No such soda");
            }

            return sodaType.map(type -> {
                SodaItem sodaItem = sodaItemFactory.createItem(type);
                return new OrderSmsSoda(inventory, account, sodaItem);
            });
        }
        else if (command.equals("recall"))
        {
            return Optional.of(new RecallMoney(inventory, account));
        }

        return Optional.empty();
    }

    public void printHelp() {
        System.out.println("\n\nAvailable commands:");
        System.out.println("insert (money) - Money put into money slot");
        System.out.println("order (coke, sprite, fanta) - Order from machines buttons");
        System.out.println("sms order (coke, sprite, fanta) - Order sent by sms");
        System.out.println("recall - gives money back");
        System.out.println("-------");
        System.out.println("Inserted money: " + account.getBalance());
        System.out.println("-------\n\n");
    }
}
