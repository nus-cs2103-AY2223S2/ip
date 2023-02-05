package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a delete command for a deleting a task from a task list.
 */
public class DeleteCommand implements Command {
    /**
     * Deletes the task specified in the input from tasks and returns an acknowledgement message.
     *
     * @param input {@inheritDoc}
     * @param tasks {@inheritDoc}
     * @return An acknowledgement message.
     * @throws DukeException Indicates missing index or non-integer index or out of bound index in input.
     */
    @Override
    public String run(String input, TaskList tasks) throws DukeException {
        assert tasks != null;

        int index = extractValidIndex(input, tasks);
        Task task = tasks.removeAt(index);

        return getMessage(tasks, task);
    }

    private int extractValidIndex(String input, TaskList tasks) throws DukeException {
        assert input != null;

        String argStr = input.replaceFirst("delete", "").trim();

        validateNonEmptyArg(argStr);

        int index = extractIntegerArg(argStr);
        validateIndexRange(index, tasks);

        return index;
    }

    private void validateNonEmptyArg(String argStr) throws DukeException {
        assert argStr != null;

        if (argStr.isEmpty()) {
            throw new DukeException("The task to be deleted must be specified!");
        }
    }

    private int extractIntegerArg(String argStr) throws DukeException {
        assert argStr != null;

        try {
            return Integer.parseInt(argStr) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("The index of the task to be deleted must be an integer!");
        }
    }

    private void validateIndexRange(int index, TaskList tasks) throws DukeException {
        assert tasks != null;

        if (index >= tasks.size() || index < 0) {
            throw new DukeException("The task to be deleted doesn't exist!");
        }
    }

    private String getMessage(TaskList tasks, Task task) {
        assert tasks != null;
        assert task != null;

        return String.format("I've removed this task:\n  %s\nNow you have %d tasks in the list.", task.toString(),
                tasks.size());
    }
}
