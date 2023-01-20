package controllers;

import entities.Task;
import entities.TaskList;
import enums.CommandType;
import exceptions.DukeException;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.regex.Matcher;

public class UnmarkCommand extends Command {
    private final String args;

    public UnmarkCommand(String args) {
        super(CommandType.UNMARK);
        this.args = args;
    }

    @Override
    public void execute(Supplier<? extends TaskList> taskList) throws DukeException {
        TaskList store = taskList.get();
        Matcher m = NUMBERS.matcher(args);
        if (m.find()) {
            Optional<Task> task = store.getTask(Integer.parseInt(m.group()));
            task.ifPresent(Task::unmarkTask);
        } else {
            throw new DukeException("Invalid format. Please ensure that you specify the task number.");
        }
    }
}
