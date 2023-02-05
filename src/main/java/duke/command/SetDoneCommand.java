package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command that sets the done status of a task.
 */
public abstract class SetDoneCommand implements Command {
    /**
     * Updates the done status of the task specified in the input and returns an acknowledgement message.
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
        Task task = tasks.get(index).setDone(shouldBeDone());

        tasks.update(index, task);

        return getMessage(task);
    }

    /**
     * Returns the name of the command.
     *
     * @return Name of the command.
     */
    protected abstract String getCommand();

    /**
     * Returns the done status to set the task to.
     *
     * @return The done status to set the task to.
     */
    protected abstract boolean shouldBeDone();

    /**
     * Returns the prefix to prepend to the response message when the command succeeds.
     *
     * @return The prefix to prepend to the response message when the command succeeds.
     */
    protected abstract String getSuccessMessagePrefix();

    private int extractValidIndex(String input, TaskList tasks) throws DukeException {
        String argStr = extractArgStr(input);

        validateNonEmptyArg(argStr);

        int index = extractIntegerArg(argStr);
        validateIndexRange(index, tasks);

        return index;
    }

    private String extractArgStr(String input) {
        assert input != null;

        return input.replaceFirst(getCommand(), "").trim();
    }

    private void validateNonEmptyArg(String argStr) throws DukeException {
        assert argStr != null;

        if (argStr.isEmpty()) {
            throw new DukeException(String.format("The task to %s must be specified!", getCommand()));
        }
    }

    private int extractIntegerArg(String argStr) throws DukeException {
        assert argStr != null;

        try {
            return Integer.parseInt(argStr) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException(String.format("The index of the task to %s must be an integer!", getCommand()));
        }
    }

    private void validateIndexRange(int index, TaskList tasks) throws DukeException {
        assert tasks != null;

        if (index >= tasks.size() || index < 0) {
            throw new DukeException(String.format("The task to %s doesn't exist!", getCommand()));
        }
    }

    private String getMessage(Task task) {
        assert task != null;

        return String.format("%s\n  %s", getSuccessMessagePrefix(), task.toString());
    }
}
