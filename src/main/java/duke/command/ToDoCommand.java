package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * Represents an add To-Do task command.
 */
public class ToDoCommand extends AddCommand {
    /**
     * Add the new to-do specified in the input to tasks and returns an acknowledgement message.
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
        String description = input.replaceFirst("todo", "").trim();

        if (description.isEmpty()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }

        return description;
    }
}
