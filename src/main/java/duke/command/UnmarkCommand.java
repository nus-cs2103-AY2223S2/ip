package duke.command;

import duke.exception.DukeException;
import duke.task.Task;

import java.util.List;

/**
 * Represents an unmark command for marking a task as not done.
 */
public class UnmarkCommand implements Command {
    /**
     * Returns a CommandResponse object containing an acknowledgement message and an updated task list with the
     * specified task's done status set to false.
     *
     * @param input {@inheritDoc}
     * @param tasks {@inheritDoc}
     * @return CommandResponse object containing an acknowledgement message and an updated task list with the specified
     * task's done status set to false.
     * @throws DukeException Indicates missing index or non-integer index or out of bound index in input
     */
    @Override
    public CommandResponse run(String input, List<Task> tasks) throws DukeException {
        String arg = input.replaceFirst("unmark ", "");

        if (arg.isEmpty()) {
            throw new DukeException("The index of an unmark cannot be empty.");
        }

        int index;
        try {
            index = Integer.parseInt(arg) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("The index of an unmark must be an integer.");
        }

        if (index >= tasks.size() || index < 0) {
            throw new DukeException("The index of an unmark must be between 1 and the number of task");
        }

        Task task = tasks.get(index);
        task = task.setDone(false);
        tasks.set(index, task);

        String message = String.format("OK, I've marked this task as not done yet:\n  %s", task.toString());

        return new CommandResponse(message, tasks);
    }
}
