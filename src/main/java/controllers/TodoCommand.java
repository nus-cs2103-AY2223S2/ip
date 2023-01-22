package controllers;

import java.util.function.Supplier;
import java.util.regex.Matcher;

import entities.Task;
import entities.TaskList;
import enums.CommandType;
import enums.TaskType;
import exceptions.DukeException;

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
    public void execute(Supplier<? extends TaskList> taskList) throws DukeException {
        TaskList store = taskList.get();
        Matcher mTodo = Task.TODO.matcher(args);
        Task.processTask(mTodo, TaskType.TODO, store);
    }
}
