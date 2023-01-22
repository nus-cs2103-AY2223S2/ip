package controllers;

import java.util.function.Supplier;
import java.util.regex.Pattern;

import entities.TaskList;
import enums.CommandType;
import exceptions.DukeException;
import utils.IExecutable;

/**
 * Command represents an abstraction over the inputs to the duke chatbot.
 */
public abstract class Command implements IExecutable<TaskList> {
    private final CommandType commandType;
    private boolean isTerminating = false;
    protected static final Pattern NUMBERS = Pattern.compile("[-+]?\\d+");
    protected static final String INVALID_FORMAT_ERROR = "Invalid format.";

    public Command(CommandType cmdType) {
        this.commandType = cmdType;
    }

    /**
     * Initializes a new Command with the specified type and termination.
     * @param cmdType A command type.
     * @param isTerminating Termination command.
     */
    public Command(CommandType cmdType, boolean isTerminating) {
        this.commandType = cmdType;
        this.isTerminating = isTerminating;
    }

    @Override
    public abstract void execute(Supplier<? extends TaskList> taskList) throws DukeException;
    public boolean isTerminating() {
        return isTerminating;
    }
}
