package duke.controllers;

import java.util.function.Supplier;
import java.util.regex.Matcher;

import duke.entities.managers.CacheManager;
import duke.enums.CommandType;
import duke.exceptions.DukeException;

/**
 * Represents the Mark Command.
 * The mark command can be used to mark a task as completed.
 */
public class MarkCommand extends Command {
    private final String args;

    /**
     * Initializes a Mark Command.
     *
     * @param args The parsed arguments.
     */
    public MarkCommand(String args) {
        super(CommandType.MARK);
        this.args = args;
    }

    /**
     * {@inheritDoc}
     * The method verifies the command and mark the specified task as complete.
     */
    @Override
    public String execute(Supplier<? extends CacheManager> taskList) throws DukeException {
        CacheManager store = taskList.get();
        Matcher m = VALID_NUMBER.matcher(args);
        if (m.find()) {
            Integer key = Integer.parseInt(m.group());
            return store.getTaskAndToggle(key, true);
        }
        throw new DukeException(INVALID_FORMAT_ERROR + " " + "Please ensure you follow: mark [task]");
    }
}
