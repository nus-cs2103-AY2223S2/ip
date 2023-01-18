package controllers;

import entities.Task;
import enums.CommandType;
import enums.TaskType;
import exceptions.DukeException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeadlineCommand extends Command {
    private final String args;
    private static final Pattern TASK_DEADLINE = Pattern.compile("^(deadline) (.+) /by (.+)$", Pattern.CASE_INSENSITIVE);

    public DeadlineCommand(String arguments) {
        super(CommandType.DEADLINE);
        this.args = arguments;
    }

    @Override
    public void execute() throws DukeException {
        Matcher mDeadline = TASK_DEADLINE.matcher(args);
        Task.processTask(mDeadline, TaskType.DEADLINE);
    }
}
