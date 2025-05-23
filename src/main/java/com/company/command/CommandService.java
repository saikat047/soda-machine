package com.company.command;

import com.company.Account;
import com.company.Inventory;
import com.company.item.SodaItem;
import com.company.item.SodaItemFactory;
import com.company.item.SodaItemType;
import com.company.util.Utils;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public class CommandService {

    public static final String COMMAND_INSERT = "insert";
    public static final String COMMAND_ORDER = "order";
    public static final String COMMAND_SMS_ORDER = "sms order";
    public static final String COMMAND_RECALL = "recall";

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
        // It is possible to entirely remove the following if-else-if conditions by using a
        // Registry of command prefixes and their respective implementation classes.
        // The amount of Reflection code needed to do this should be considered beyond the scope
        // of an interview exercise. It's highly encouraged for production quality code though
        // due to the flexibility of writing new commands.
        if (command.startsWith(COMMAND_INSERT)) {
            Optional<String> argument = getCommandArgument(COMMAND_INSERT, command);
            Optional<Long> moneyOpt = argument.map(CommandService::getMoneyToInsert).flatMap(Function.identity());
            if (moneyOpt.isEmpty()) {
                System.err.println("Invalid money amount: '" + argument.orElse("") + "'");
            }
            return moneyOpt.map(money -> new InsertMoney(inventory, account, money));
        }
        else if (command.startsWith(COMMAND_ORDER)) {
            Optional<String> argument = getCommandArgument(COMMAND_ORDER, command);
            Optional<SodaItemType> sodaType = argument.map(SodaItemType::findType).flatMap(Function.identity());
            if (sodaType.isEmpty()) {
                System.out.println("No such soda");
            }

            return sodaType.map(type -> {
                SodaItem sodaItem = sodaItemFactory.createItem(type);
                return new OrderSoda(inventory, account, sodaItem);
            });
        }
        else if (command.startsWith(COMMAND_SMS_ORDER)) {
            Optional<String> argument = getCommandArgument(COMMAND_SMS_ORDER, command);
            Optional<SodaItemType> sodaType = argument.map(SodaItemType::findType).flatMap(Function.identity());
            if (sodaType.isEmpty()) {
                System.out.println("No such soda");
            }

            return sodaType.map(type -> {
                SodaItem sodaItem = sodaItemFactory.createItem(type);
                return new OrderSmsSoda(inventory, account, sodaItem);
            });
        }
        else if (command.equals(COMMAND_RECALL))
        {
            return Optional.of(new RecallMoney(inventory, account));
        }

        return Optional.empty();
    }

    private static Optional<String> getCommandArgument(String commandPrefix, String command) {
        String args = command.replaceAll(commandPrefix, "").replaceAll(" ", "");
        if (!Utils.isEmpty(args)) {
            return Optional.of(args);
        }
        return Optional.empty();
    }

    private static Optional<Long> getMoneyToInsert(String numberStr) {
        try {
            long number = Long.parseLong(numberStr);
            if (number <= 0) {
                System.err.println("Must insert positive number for money");
                return Optional.empty();
            }
            return Optional.of(number);
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
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
