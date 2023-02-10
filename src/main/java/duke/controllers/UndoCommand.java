package duke.controllers;

import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.entities.managers.CacheManager;
import duke.enums.CommandType;
import duke.exceptions.DukeException;

/**
 * Represents the undo command.
 * The command reverts the state of the program to a previous executed command.
 */
public class UndoCommand extends Command {
    private static final Pattern VALID_CMD = Pattern.compile("^(?<cmd>undo)$");
    private final String args;

    /**
     * Initializes the undo command.
     *
     * @param args The parsed arguments.
     */
    public UndoCommand(String args) {
        super(CommandType.UNDO);
        this.args = args;
    }

    /**
     * The function to call when the command is executed in the main event loop.
     *
     * @param store In-memory store that holds all existing tasks.
     * @throws DukeException Throws an exception when something goes wrong.
     */
    @Override
    public String execute(Supplier<? extends CacheManager> store) throws DukeException {
        CacheManager cacheManager = store.get();
        Matcher matcher = VALID_CMD.matcher(args);
        if (matcher.find()) {
            return cacheManager.undo();
        } else {
            throw new DukeException(INVALID_FORMAT_ERROR + " " + "Please ensure you follow: undo");
        }
    }
}
