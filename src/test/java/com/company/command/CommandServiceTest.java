package com.company.command;

import com.company.Account;
import com.company.Inventory;
import com.company.item.SodaItem;
import com.company.item.SodaItemFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.company.item.SodaItemType.COKE;
import static com.company.item.SodaItemType.FANTA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommandServiceTest {

    private Inventory inventory;

    private Account account;

    private SodaItemFactory sodaItemFactory;

    private CommandService commandService;
    private SodaItem cokeItem;
    private SodaItem fantaItem;

    @BeforeEach
    void setUp() {
        account = new Account();
        account.addAmount(100);
        inventory = new Inventory();
        sodaItemFactory = new SodaItemFactory();
        cokeItem = sodaItemFactory.createItem(COKE);
        inventory.addItem(cokeItem, 1);
        fantaItem = sodaItemFactory.createItem(FANTA);
        inventory.addItem(fantaItem, 1);
        commandService = new CommandService(inventory, account, sodaItemFactory);
    }

    @Test
    void createCommand_insertMoney_returnsInsertMoneyCommand() {
        Optional<Command<?>> commandOpt = commandService.createCommand("insert 50");

        assertTrue(commandOpt.isPresent());
        assertInstanceOf(InsertMoney.class, commandOpt.get());
    }

    @Test
    void createCommand_orderValidSoda_returnsOrderSodaCommand() {
        Optional<Command<?>> commandOpt = commandService.createCommand("order coke");
        assertTrue(commandOpt.isPresent());
        assertInstanceOf(OrderSoda.class, commandOpt.get());
        commandOpt.get().execute();
        assertEquals(0, inventory.getItemCount(cokeItem), 0);
        assertEquals(1, inventory.getItemCount(fantaItem), 0);
    }

    @Test
    void createCommand_orderInvalidSoda_returnsEmptyOptional() {
        Optional<Command<?>> commandOpt = commandService.createCommand("order mountaindew");
        assertTrue(commandOpt.isEmpty());
    }

    @Test
    void createCommand_smsOrderValidSoda_returnsOrderSmsSodaCommand() {
        Optional<Command<?>> commandOpt = commandService.createCommand("sms order fanta");

        assertTrue(commandOpt.isPresent(), "Command should be present for valid 'sms order SPRITE'");
        assertInstanceOf(OrderSmsSoda.class, commandOpt.get());
    }

    @Test
    @DisplayName("createCommand: 'recall' command should create RecallMoney command")
    void createCommand_recall_returnsRecallMoneyCommand() {
        Optional<Command<?>> commandOpt = commandService.createCommand("recall");

        assertTrue(commandOpt.isPresent());
        assertInstanceOf(RecallMoney.class, commandOpt.get());
        commandOpt.get().execute();
        assertEquals(0L, account.getBalance());
    }

    @Test
    @DisplayName("createCommand: unknown command should return empty Optional")
    void createCommand_unknownCommand_returnsEmptyOptional() {
        Optional<Command<?>> commandOpt = commandService.createCommand("who are you?");
        assertTrue(commandOpt.isEmpty());
    }
}