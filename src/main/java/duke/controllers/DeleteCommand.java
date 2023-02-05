package duke.controllers;

import java.util.function.Supplier;
import java.util.regex.Matcher;

import duke.entities.managers.CacheManager;
import duke.enums.CommandType;
import duke.exceptions.DukeException;

/**
 * Represents the Delete Command.
 * The delete command can be used to remove a task.
 */
public class DeleteCommand extends Command {
    private final String args;

    /**
     * Initializes a Delete Command.
     *
     * @param args The parsed arguments.
     */
    public DeleteCommand(String args) {
        super(CommandType.DELETE);
        this.args = args;
    }

    /**
     * {@inheritDoc}
     * The method verifies the command and delete the task specified.
     */
    @Override
    public String execute(Supplier<? extends CacheManager> taskList) throws DukeException {
        CacheManager store = taskList.get();
        Matcher m = VALID_NUMBER.matcher(args);
        if (m.find()) {
            return store.deleteTask(Integer.parseInt(m.group()));
        } else {
            throw new DukeException(INVALID_FORMAT_ERROR + " " + "Please ensure that you specify the task number.");
        }
    }
}
