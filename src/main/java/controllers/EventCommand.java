package controllers;

import entities.Task;
import enums.CommandType;
import enums.TaskType;
import exceptions.DukeException;

import java.util.regex.Matcher;

public class EventCommand extends Command {
    private final String args;


    public EventCommand(String arguments) {
        super(CommandType.EVENT);
        this.args = arguments;
    }
    @Override
    public void execute() throws DukeException {
        Matcher mEvent = Task.EVENT.matcher(args);
        Task.processTask(mEvent, TaskType.EVENT);
    }
}
