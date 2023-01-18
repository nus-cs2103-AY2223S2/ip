package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a delete command.
 */
public class DeleteCommand implements Command {
    /**
     * Delete the task specified in the input from tasks and return an acknowledgement message.
     *
     * @param input {@inheritDoc}
     * @param tasks {@inheritDoc}
     * @return An acknowledgement message.
     * @throws DukeException Indicates missing index or non-integer index or out of bound index in input.
     */
    @Override
    public String run(String input, TaskList tasks) throws DukeException {
        int index = extractValidIndex(input, tasks);
        Task task = tasks.removeAt(index);
        return getMessage(tasks, task);
    }

    private int extractValidIndex(String input, TaskList tasks) throws DukeException {
        String argStr = input.replaceFirst("delete", "").trim();

        if (argStr.isEmpty()) {
            throw new DukeException("The task to be deleted must be specified.");
        }

        int index;
        try {
            index = Integer.parseInt(argStr) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("The index of the task to be deleted must be an integer.");
        }

        if (index >= tasks.size() || index < 0) {
            throw new DukeException("The task to be deleted must exist.");
        }

        return index;
    }

    private String getMessage(TaskList tasks, Task task) {
        return String.format("Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.",
                task.toString(), tasks.size());
    }
}
