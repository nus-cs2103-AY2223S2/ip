package duke.command;

import duke.exception.DukeException;
import duke.task.Task;

import java.util.List;

/**
 * Represents a mark command for marking a task as done.
 */
public class MarkCommand implements Command {
    /**
     * Returns a CommandResponse object containing an acknowledgement message and an updated task list with the
     * specified task's done status set to true.
     *
     * @param input {@inheritDoc}
     * @param tasks {@inheritDoc}
     * @return CommandResponse object containing an acknowledgement message and an updated task list with the specified
     * task's done status set to true.
     * @throws DukeException Indicates missing index or non-integer index or out of bound index in input
     */
    @Override
    public CommandResponse run(String input, List<Task> tasks) throws DukeException {
        String arg = input.replaceFirst("mark ", "");

        if (arg.isEmpty()) {
            throw new DukeException("The index of an mark cannot be empty.");
        }

        int index;
        try {
            index = Integer.parseInt(arg) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("The index of an mark must be an integer.");
        }

        if (index >= tasks.size() || index < 0) {
            throw new DukeException("The index of an mark must be between 1 and the number of task");
        }

        Task task = tasks.get(index);
        task = task.setDone(true);
        tasks.set(index, task);

        String message = String.format("Nice! I've marked this task as done:\n  %s", task.toString());

        return new CommandResponse(message, tasks);
    }
}
