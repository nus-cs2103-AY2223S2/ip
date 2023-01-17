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
        int index = extractValidIndex(input, tasks);
        List<Task> updatedTasks = getUpdatedTasks(tasks, index);
        String message = getMessage(updatedTasks, tasks.get(index));

        return new CommandResponse(message, updatedTasks);
    }

    private int extractValidIndex(String input, List<Task> tasks) throws DukeException {
        String argStr = input.replaceFirst("delete", "").trim();

        if (argStr.isEmpty()) {
            throw new DukeException("The task to be deleted must be specified.");
        }

        int index;
        try {
            index = Integer.parseInt(argStr) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("The index of the task to be deleted must be an integer.");
        }

        if (index >= tasks.size() || index < 0) {
            throw new DukeException("The task to be deleted must exist.");
        }

        return index;
    }

    private List<Task> getUpdatedTasks(List<Task> tasks, int index) {
        List<Task> updatedTasks = new ArrayList<Task>(tasks);
        updatedTasks.remove(index);

        return updatedTasks;
    }

    private String getMessage(List<Task> tasks, Task task) {
        return String.format("Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.",
                task.toString(), tasks.size());
    }
}
