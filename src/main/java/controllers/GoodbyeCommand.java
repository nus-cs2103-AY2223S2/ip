package controllers;

import java.util.function.Supplier;

import entities.TaskList;
import enums.CommandType;

/**
 * Represents the Goodbye Command.
 * The goodbye command can be used to terminate the program and save date to hard disk.
 */
public class GoodbyeCommand extends Command {
    private static final String message = "Goodbye!";

    public GoodbyeCommand() {
        super(CommandType.BYE, true);
    }

    @Override
    public void execute(Supplier<? extends TaskList> taskList) {
        System.out.println(message);
    }
}
