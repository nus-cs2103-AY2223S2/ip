package controllers;

import entities.Task;
import entities.TaskList;
import enums.CommandType;
import exceptions.DukeException;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.regex.Matcher;

public class MarkCommand extends Command {
    private final String args;

    public MarkCommand(String args) {
        super(CommandType.MARK);
        this.args = args;
    }

    /**
     * {@inheritDoc}
     * The method verifies the command and mark the specified task as complete.
     */
    @Override
    public void execute(Supplier<? extends TaskList> taskList) throws DukeException {
        TaskList store = taskList.get();
        Matcher m = VALID_NUMBER.matcher(args);
        if (m.find()) {
            Optional<Task> task = store.getTask(Integer.parseInt(m.group()));
            task.ifPresent(Task::markTask);
        } else {
            throw new DukeException("Invalid format. Please ensure that you specify the task number.");
        }
    }
}
