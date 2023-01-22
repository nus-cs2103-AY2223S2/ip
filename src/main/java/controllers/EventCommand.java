package controllers;

import entities.Task;
import entities.TaskList;
import enums.CommandType;
import enums.TaskType;
import exceptions.DukeException;

import java.util.function.Supplier;
import java.util.regex.Matcher;

public class EventCommand extends Command {
    private final String args;


    public EventCommand(String arguments) {
        super(CommandType.EVENT);
        this.args = arguments;
    }

    /**
     * {@inheritDoc}
     * The method verifies the command and add a new task with the specified from and to dates.
     */
    @Override
    public void execute(Supplier<? extends TaskList> taskList) throws DukeException {
        TaskList store = taskList.get();
        Matcher mEvent = Task.EVENT.matcher(args);
        Task.processTask(mEvent, TaskType.EVENT, store);
    }
}
