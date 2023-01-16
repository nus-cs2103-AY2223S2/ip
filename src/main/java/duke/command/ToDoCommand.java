package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.ToDo;

import java.util.List;

/**
 * Represents an add To-Do task command.
 */
public class ToDoCommand extends AddCommand {
    /**
     * {@inheritDoc}
     *
     * @param input {@inheritDoc}
     * @param tasks {@inheritDoc}
     * @return {@inheritDoc}
     * @throws DukeException Indicates empty description in input.
     */
    @Override
    public CommandResponse run(String input, List<Task> tasks) throws DukeException {
        return super.run(input, tasks);
    }

    /**
     * {@inheritDoc}
     *
     * @param input {@inheritDoc}
     * @return {@inheritDoc}
     * @throws DukeException Indicates empty description in input.
     */
    @Override
    protected Task createTask(String input) throws DukeException {
        String description = input.replaceFirst("todo ", "").trim();

        if (description.isEmpty()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }

        return new ToDo(false, description);
    }
}
