package controllers;

import entities.Task;
import entities.TaskList;
import enums.CommandType;
import exceptions.DukeException;

import java.util.Optional;
import java.util.regex.Matcher;

public class MarkCommand extends Command {
    private final String args;

    public MarkCommand(String args) {
        super(CommandType.MARK);
        this.args = args;
    }

    @Override
    public void execute() throws DukeException {
        Matcher m = NUMBERS.matcher(args);
        if (m.find()) {
            Integer key = Integer.parseInt(m.group());
            Optional<Task> task = TaskList.getTask(key);
            task.ifPresent(Task::markTask);
        } else {
            throw new DukeException("Invalid format. Please ensure that you specify the task number.");
        }
    }
}
