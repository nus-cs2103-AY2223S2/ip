package services;

import types.ICommand;

import java.util.ArrayList;

public class Parser {
    private final ArrayList<Class<ICommand>> commandRegistry = new ArrayList<>();
    private Class<ICommand> defaultCommand;
    private Class<ICommand> exitCommand;
    private Runnable toExit;

    @SuppressWarnings("unused")
    public void registerCommand(Class<ICommand> c) {
        this.commandRegistry.add(c);
    }

    public void setDefaultCommand(Class<ICommand> defaultCommand) {
        this.defaultCommand = defaultCommand;
    }

    public void setExitCommand(Class<ICommand> exitCommand) {
        this.exitCommand = exitCommand;
    }

    public void setToExit(Runnable toExit) {
        this.toExit = toExit;
    }

    public void handle(String expr) {
        if (CommandHelper.canTake(this.exitCommand, expr)) {
            CommandHelper.take(this.exitCommand, expr);
            toExit.run();
            return;
        }

        for (Class<ICommand> c : commandRegistry) {
            if (CommandHelper.canTake(c, expr)) {
                CommandHelper.take(c, expr);
                return;
            }
        }

        CommandHelper.take(this.defaultCommand, expr);
    }
}
