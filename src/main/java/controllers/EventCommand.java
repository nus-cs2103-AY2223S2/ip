package controllers;

import entities.Task;
import enums.CommandType;
import enums.TaskType;
import exceptions.DukeException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventCommand extends Command {
    private final String args;
    private static final Pattern TASK_EVENT = Pattern.compile("^(event) (.+) /from (.+) /to (.+)$", Pattern.CASE_INSENSITIVE);

    public EventCommand(String arguments) {
        super(CommandType.EVENT);
        this.args = arguments;
    }
    @Override
    public void execute() throws DukeException {
        Matcher mEvent = TASK_EVENT.matcher(args);
        Task.processTask(mEvent, TaskType.EVENT);
    }
}
