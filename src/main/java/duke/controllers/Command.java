package duke.controllers;

import java.util.function.Supplier;
import java.util.regex.Pattern;

import duke.entities.managers.CacheManager;
import duke.enums.CommandType;
import duke.exceptions.DukeException;
import duke.utils.IExecutable;

/**
 * Command represents an abstraction over the inputs to the duke chatbot.
 */
public abstract class Command implements IExecutable<CacheManager> {
    protected static final String INVALID_FORMAT_ERROR = "Invalid format!";
    protected static final Pattern VALID_NUMBER = Pattern.compile("[-+]?\\d+");

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
    public abstract String execute(Supplier<? extends CacheManager> store) throws DukeException;

    public boolean isTerminating() {
        return isTerminating;
    }
}
