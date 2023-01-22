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
    protected static final String INVALID_FORMAT_ERROR = "Invalid format.";
    protected static final Pattern VALID_NUMBER = Pattern.compile("[-+]?\\d+");
    protected static final Pattern VALID_DATE =
            Pattern.compile("(?<year>\\d{4})-(?<month>0[0-9]|1[0-2])-(?<day>0[0-9]|1[0-9]|2[0-9]|3[0-1])$");

    /** The associated type of command **/
    private final CommandType commandType;
    /** Indicates if the program should terminate after executing the command **/
    private boolean isTerminating = false;

    public Command(CommandType cmdType) {
        this.commandType = cmdType;
    }

    /**
     * Initializes a new Command with the specified type and termination.
     *
     * @param cmdType A command type.
     * @param isTerminating Termination command.
     */
    public Command(CommandType cmdType, boolean isTerminating) {
        this.commandType = cmdType;
        this.isTerminating = isTerminating;
    }


    /**
     * The function to call when the command is executed in the main event loop.
     *
     * @param store In-memory store that holds all existing tasks.
     * @throws DukeException Throws an exception when something goes wrong.
     */
    @Override
    public abstract void execute(Supplier<? extends TaskList> store) throws DukeException;

    public boolean isTerminating() {
        return isTerminating;
    }
}
