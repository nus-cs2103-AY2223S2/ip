package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * Represents an add to-do command for adding a to-do to a task list.
 */
public class ToDoCommand extends AddCommand {
    /**
     * Creates a to-do using the specified input, adds it to tasks, and returns an acknowledgement message.
     *
     * @param input {@inheritDoc}
     * @param tasks {@inheritDoc}
     * @return {@inheritDoc}
     * @throws DukeException Indicates empty description in input.
     */
    @Override
    public String run(String input, TaskList tasks) throws DukeException {
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
        String description = extractValidDescription(input);
        return new ToDo(false, description);
    }

    private String extractValidDescription(String input) throws DukeException {
        String description = extractDescription(input);

        validateNonEmptyDescription(description);

        return description;
    }

    private String extractDescription(String input) {
        assert input != null;

        return input.replaceFirst("todo", "").trim();
    }

    private void validateNonEmptyDescription(String description) throws DukeException {
        assert description != null;

        if (description.isEmpty()) {
            throw new DukeException("The description of a to-do cannot be empty!");
        }
    }
}
