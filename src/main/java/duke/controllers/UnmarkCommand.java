package duke.controllers;

import java.util.function.Supplier;
import java.util.regex.Matcher;

import duke.entities.managers.CacheManager;
import duke.enums.CommandType;
import duke.exceptions.DukeException;

/**
 * Represents the Unmark Command.
 * The unmark command can be used to unmark a task.
 */
public class UnmarkCommand extends Command {
    private final String args;

    /**
     * Initializes an unmark Command.
     *
     * @param args The parsed arguments.
     */
    public UnmarkCommand(String args) {
        super(CommandType.UNMARK);
        this.args = args;
    }

    /**
     * {@inheritDoc}
     * The method verifies the command and unmark the specified task as incomplete.
     */
    @Override
    public String execute(Supplier<? extends CacheManager> taskList) throws DukeException {
        CacheManager store = taskList.get();
        Matcher m = VALID_NUMBER.matcher(args);
        if (m.find()) {
            Integer key = Integer.parseInt(m.group());
            return store.getTaskAndToggle(key, false);
        }
        throw new DukeException(INVALID_FORMAT_ERROR + " " + "Please ensure that you specify the task number.");
    }
}
