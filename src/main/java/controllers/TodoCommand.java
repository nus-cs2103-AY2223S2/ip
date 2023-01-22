package controllers;

import entities.Task;
import entities.TaskList;
import enums.CommandType;
import enums.TaskType;
import exceptions.DukeException;

import java.util.function.Supplier;
import java.util.regex.Matcher;

public class TodoCommand extends Command {
    private final String args;

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
