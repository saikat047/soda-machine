package com.company.command;

import com.company.Account;
import com.company.Inventory;

import java.util.Objects;

public abstract class Command<T> {
    private final String commandPrefix;
    protected final Inventory inventory;
    protected final Account account;
    private final T param;

    public Command(String commandPrefix, Inventory inventory, Account account) {
        this(commandPrefix, inventory, account, null);
    }

    public Command(String commandPrefix, Inventory inventory, Account account, T param) {
        this.commandPrefix = Objects.requireNonNull(commandPrefix);
        this.inventory = Objects.requireNonNull(inventory);
        this.account = Objects.requireNonNull(account);
        this.param = param;
    }

    public String getCommandPrefix() {
        return commandPrefix;
    }

    public T getParam() {
        return param;
    }

    public abstract void execute();
}
