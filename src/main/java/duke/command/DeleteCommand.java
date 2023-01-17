package duke.command;

import duke.exception.DukeException;
import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a delete command.
 */
public class DeleteCommand implements Command {
    /**
     * Returns a CommandResponse object containing an acknowledgement message and an updated task list with the
     * specified task removed.
     *
     * @param input {@inheritDoc}
     * @param tasks {@inheritDoc}
     * @return CommandResponse object containing an acknowledgement message and an updated task list with the specified
     * task removed.
     * @throws DukeException Indicates missing index or non-integer index or out of bound index in input.
     */
    @Override
    public CommandResponse run(String input, List<Task> tasks) throws DukeException {
        String arg = input.replaceFirst("delete ", "");

        if (arg.isEmpty()) {
            throw new DukeException("The index of a delete cannot be empty.");
        }

        int index;
        try {
            index = Integer.parseInt(arg) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("The index of a delete must be an integer.");
        }

        if (index >= tasks.size() || index < 0) {
            throw new DukeException("The index of a delete must be between 1 and the number of task.");
        }

        List<Task> updatedTasks = new ArrayList<Task>(tasks);
        Task task = updatedTasks.remove(index);

        String message = String.format("Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.",
                task.toString(), updatedTasks.size());

        return new CommandResponse(message, updatedTasks);
    }
}
