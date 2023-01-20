package controllers;

import java.util.function.Supplier;
import java.util.regex.Matcher;

import entities.Task;
import entities.TaskList;
import enums.CommandType;
import enums.TaskType;
import exceptions.DukeException;

/**
 * Represents the Event Command.
 * The event command can be used to add a new task with a from date and to date.
 */
public class EventCommand extends Command {
    private final String args;

    /**
     * Initializes an Event Command.
     * @param arguments The parsed arguments.
     */
    public EventCommand(String arguments) {
        super(CommandType.EVENT);
        this.args = arguments;
    }
    @Override
    public void execute(Supplier<? extends TaskList> taskList) throws DukeException {
        TaskList store = taskList.get();
        Matcher mEvent = Task.EVENT.matcher(args);
        Task.processTask(mEvent, TaskType.EVENT, store);
    }
}
