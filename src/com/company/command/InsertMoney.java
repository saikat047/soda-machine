package com.company.command;

import com.company.Account;
import com.company.Inventory;

import java.util.Objects;

public class InsertMoney extends Command<Long> {
    public InsertMoney(Inventory inventory, Account account, Long amount) {
        super("insert", inventory, account, Objects.requireNonNull(amount));
    }

    @Override
    public void execute() {
        System.out.println("Adding " + getParam() + " to credit");
        account.addAmount(getParam());
    }
}
