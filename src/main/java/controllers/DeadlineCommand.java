package controllers;

import entities.*;
import enums.CommandType;
import enums.TaskType;
import exceptions.DukeException;
import java.util.regex.Matcher;

public class DeadlineCommand extends Command {
    private final String args;

    public DeadlineCommand(String arguments) {
        super(CommandType.DEADLINE);
        this.args = arguments;
    }

    @Override
    public void execute() throws DukeException {
        Matcher mDeadline = Task.DEADLINE.matcher(args);
        Task.processTask(mDeadline, TaskType.DEADLINE);
    }
}
