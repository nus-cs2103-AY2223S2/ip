package controllers;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.regex.Matcher;

import entities.Task;
import entities.TaskList;
import enums.CommandType;
import exceptions.DukeException;

/**
 * Represents the Mark Command.
 * The mark command can be used to mark a task as completed.
 */
public class MarkCommand extends Command {
    private final String args;

    /**
     * Initializes a Mark Command.
     * @param args The parsed arguments.
     */
    public MarkCommand(String args) {
        super(CommandType.MARK);
        this.args = args;
    }

    @Override
    public void execute(Supplier<? extends TaskList> taskList) throws DukeException {
        TaskList store = taskList.get();
        Matcher m = NUMBERS.matcher(args);
        if (m.find()) {
            Optional<Task> task = store.getTask(Integer.parseInt(m.group()));
            task.ifPresent(Task::markTask);
        } else {
            throw new DukeException("Invalid format. Please ensure that you specify the task number.");
        }
    }
}
