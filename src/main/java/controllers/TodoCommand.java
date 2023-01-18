package controllers;

import entities.Task;
import enums.CommandType;
import enums.TaskType;
import exceptions.DukeException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TodoCommand extends Command {
    private static final Pattern TASK_TODO = Pattern.compile("^(todo) (.+)$", Pattern.CASE_INSENSITIVE);
    private final String args;

    public TodoCommand(String arguments) {
        super(CommandType.TODO);
        this.args = arguments;
    }

    @Override
    public void execute() throws DukeException {
        Matcher mTodo = TASK_TODO.matcher(args);
        Task.processTask(mTodo, TaskType.TODO);
    }
}
