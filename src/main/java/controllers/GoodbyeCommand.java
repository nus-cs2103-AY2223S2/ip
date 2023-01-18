package controllers;

import enums.CommandType;

public class GoodbyeCommand extends Command {
    private static final String message = "Goodbye!";

    public GoodbyeCommand() {
        super(CommandType.BYE, true);
    }

    @Override
    public void execute() {
        System.out.println(message);
    }
}
