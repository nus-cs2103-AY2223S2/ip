package controllers;

import entities.*;
import enums.CommandType;
import enums.TaskType;
import exceptions.DukeException;

import java.util.function.Supplier;
import java.util.regex.Matcher;

public class DeadlineCommand extends Command {
    private final String args;

    public DeadlineCommand(String arguments) {
        super(CommandType.DEADLINE);
        this.args = arguments;
    }

    /**
     * {@inheritDoc}
     * This method verify the command and add a new task with the deadline specified.
     */
    @Override
    public void execute(Supplier<? extends TaskList> taskList) throws DukeException {
        TaskList store = taskList.get();
        Matcher mDeadline = Task.DEADLINE.matcher(args);
        Task.processTask(mDeadline, TaskType.DEADLINE, store);
    }
}
