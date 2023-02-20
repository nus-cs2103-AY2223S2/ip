package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents an add command for adding task to a task list.
 */
public abstract class AddCommand implements Command {
    /**
     * Creates a task using the specified input, adds it to tasks, and returns an acknowledgement message.
     *
     * @param input {@inheritDoc}
     * @param tasks {@inheritDoc}
     * @return An acknowledgement message.
     * @throws DukeException {@inheritDoc}
     */
    @Override
    public String run(String input, TaskList tasks) throws DukeException {
        assert tasks != null;

        Task task = createTask(input);
        tasks.add(task);

        return String.format("A perfect task for a simpleton like you. I've added this task:\n  %s\nNow you have %d "
                + "tasks in the list.", task.toString(), tasks.size());
    }

    /**
     * Returns a Task object created using the provided input.
     *
     * @param input User's input.
     * @return Task object created using the provided input.
     * @throws DukeException Indicates an error in the specified input.
     */
    protected abstract Task createTask(String input) throws DukeException;
}
