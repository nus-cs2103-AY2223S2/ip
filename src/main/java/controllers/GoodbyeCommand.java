package controllers;

import entities.TaskList;
import enums.CommandType;

import java.util.function.Supplier;

public class GoodbyeCommand extends Command {
    private static final String message = "Goodbye!";

    public GoodbyeCommand() {
        super(CommandType.BYE, true);
    }

    /**
     * {@inheritDoc}
     * The method returns a terminating goodbye message.
     */
    @Override
    public void execute(Supplier<? extends TaskList> taskList) {
        System.out.println(message);
    }
}
