package com.company.command;

import com.company.Account;
import com.company.Inventory;

public class RecallMoney extends Command<Void> {
    public RecallMoney(Inventory inventory, Account account) {
        super(inventory, account, null);
    }

    @Override
    public void execute() {
        // An event can be fired here to instruct the vending machine to return the money
        long balance = account.getBalance();
        System.out.println("Returning " + balance + " to customer");
        account.reset();
    }
}
