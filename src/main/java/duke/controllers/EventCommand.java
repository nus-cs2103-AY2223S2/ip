package duke.controllers;

import java.util.function.Supplier;
import java.util.regex.Matcher;

import duke.entities.Task;
import duke.entities.TaskList;
import duke.enums.CommandType;
import duke.enums.TaskType;
import duke.exceptions.DukeException;

/**
 * Represents the Event Command.
 * The event command can be used to add a new task with a from date and to date.
 */
public class EventCommand extends Command {
    private final String args;

    /**
     * Initializes an Event Command.
     *
     * @param arguments The parsed arguments.
     */
    public EventCommand(String arguments) {
        super(CommandType.EVENT);
        this.args = arguments;
    }

    /**
     * {@inheritDoc}
     * The method verifies the command and add a new task with the specified from and to dates.
     */
    @Override
    public String execute(Supplier<? extends TaskList> taskList) throws DukeException {
        TaskList store = taskList.get();
        Matcher mEvent = Task.EVENT.matcher(args);
        return Task.processTask(mEvent, TaskType.EVENT, store);
    }
}
