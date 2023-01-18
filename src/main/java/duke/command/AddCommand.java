package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents an add command.
 */
public abstract class AddCommand implements Command {
    /**
     * Add the new task specified in the input to tasks and returns an acknowledgement message.
     *
     * @param input {@inheritDoc}
     * @param tasks {@inheritDoc}
     * @return An acknowledgement message.
     * @throws DukeException {@inheritDoc}
     */
    @Override
    public String run(String input, TaskList tasks) throws DukeException {
        Task task = createTask(input);
        tasks.add(task);

        return String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.", task.toString(),
                tasks.size());
    }

    /**
     * Returns a Task object created using the provided input.
     *
     * @param input User's input.
     * @return Task object created using the provided input.
     * @throws DukeException Indicates an error in the input.
     */
    protected abstract Task createTask(String input) throws DukeException;
}
