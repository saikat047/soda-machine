package com.company;

import com.company.command.CommandService;
import com.company.item.SodaItemFactory;
import com.company.machine.SodaMachine;

import static com.company.item.SodaItemType.COKE;
import static com.company.item.SodaItemType.FANTA;
import static com.company.item.SodaItemType.SPRITE;

public class Main {

    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        SodaItemFactory sodaItemFactory = new SodaItemFactory();
        initiateInventory(inventory, sodaItemFactory);
        Account account = new Account();
        CommandService commandService = new CommandService(inventory, account, sodaItemFactory);
        SodaMachine sodaMachine = new SodaMachine(commandService);
        sodaMachine.start();
    }

    private static void initiateInventory(Inventory inventory, SodaItemFactory factory) {
        inventory.addItem(factory.createItem(COKE), 5);
        inventory.addItem(factory.createItem(SPRITE), 3);
        inventory.addItem(factory.createItem(FANTA), 3);
    }
}
