package controllers;

import entities.Task;
import enums.CommandType;
import enums.TaskType;
import exceptions.DukeException;

import java.util.regex.Matcher;

public class TodoCommand extends Command {
    private final String args;

    public TodoCommand(String arguments) {
        super(CommandType.TODO);
        this.args = arguments;
    }

    @Override
    public void execute() throws DukeException {
        Matcher mTodo = Task.TODO.matcher(args);
        Task.processTask(mTodo, TaskType.TODO);
    }
}
