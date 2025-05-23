package com.company.command;

import com.company.Account;
import com.company.Inventory;

import java.util.Objects;

public abstract class Command<T> {
    protected final Inventory inventory;
    protected final Account account;
    private final T param;

    public Command(Inventory inventory, Account account, T param) {
        this.inventory = Objects.requireNonNull(inventory);
        this.account = Objects.requireNonNull(account);
        this.param = param;
    }

    public T getParam() {
        return param;
    }

    public abstract void execute();
}
