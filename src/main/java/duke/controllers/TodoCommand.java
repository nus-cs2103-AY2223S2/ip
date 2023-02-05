package duke.controllers;

import java.util.function.Supplier;
import java.util.regex.Matcher;

import duke.entities.Task;
import duke.entities.managers.CacheManager;
import duke.enums.CommandType;
import duke.enums.TaskType;
import duke.exceptions.DukeException;

/**
 * Represents the Todo Command.
 * The todo command can be used to add a new task.
 */
public class TodoCommand extends Command {
    private final String args;

    /**
     * Initializes the Todo Command.
     *
     * @param arguments The parsed arguments.
     */
    public TodoCommand(String arguments) {
        super(CommandType.TODO);
        this.args = arguments;
    }

    /**
     * {@inheritDoc}
     * The method verifies the command and adds a new todo task into the store.
     */
    @Override
    public String execute(Supplier<? extends CacheManager> taskList) throws DukeException {
        CacheManager store = taskList.get();
        Matcher mTodo = Task.TODO.matcher(args);
        return Task.processTask(mTodo, TaskType.TODO, store);
    }
}
