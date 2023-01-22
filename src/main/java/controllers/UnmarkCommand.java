package controllers;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.regex.Matcher;

import entities.Task;
import entities.TaskList;
import enums.CommandType;
import exceptions.DukeException;

/**
 * Represents the Unmark Command.
 * The unmark command can be used to unmark a task.
 */
public class UnmarkCommand extends Command {
    private final String args;

    /**
     * Initializes an unmark Command.
     *
     * @param args The parsed arguments.
     */
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
