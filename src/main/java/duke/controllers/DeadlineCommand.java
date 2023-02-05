package duke.controllers;

import java.util.function.Supplier;
import java.util.regex.Matcher;

import duke.entities.Task;
import duke.entities.managers.CacheManager;
import duke.enums.CommandType;
import duke.enums.TaskType;
import duke.exceptions.DukeException;

/**
 * Represents the Deadline Command.
 * The deadline command can be used to add a new task with a deadline.
 */
public class DeadlineCommand extends Command {
    private final String args;

    /**
     * Initializes a Deadline Command.
     *
     * @param arguments The parsed arguments.
     */
    public DeadlineCommand(String arguments) {
        super(CommandType.DEADLINE);
        this.args = arguments;
    }

    /**
     * {@inheritDoc}
     * This method verify the command and add a new task with the deadline specified.
     */
    @Override
    public String execute(Supplier<? extends CacheManager> taskList) throws DukeException {
        CacheManager store = taskList.get();
        Matcher mDeadline = Task.DEADLINE.matcher(args);
        return Task.processTask(mDeadline, TaskType.DEADLINE, store);
    }
}
