package com.company.machine;

import com.company.command.Command;
import com.company.command.CommandService;
import com.company.util.Utils;

import java.util.Optional;
import java.util.Scanner;

public class SodaMachine
{
    private static int money;

    private final CommandService commandService;

    public SodaMachine(CommandService commandService) {
        this.commandService = commandService;
    }

    /// <summary>
    /// This is the starter method for the machine
    /// </summary>
    public void start()
    {
        String input;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            commandService.printHelp();
            input = scanner.nextLine();
            if (Utils.isEmpty(input)) {
                continue;
            }
            Optional<Command<?>> command = commandService.createCommand(input);
            command.ifPresent(Command::execute);
        }
    }
}
