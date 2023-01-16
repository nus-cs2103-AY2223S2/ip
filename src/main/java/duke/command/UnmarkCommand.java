package duke.command;

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
     */
    @Override
    public CommandResponse run(String input, List<Task> tasks) {
        String arg = input.replaceFirst("unmark ", "");
        int index = Integer.parseInt(arg) - 1;

        Task task = tasks.get(index);
        task = task.setDone(false);
        tasks.set(index, task);

        String message = String.format("OK, I've marked this task as not done yet:\n  %s", task.toString());

        return new CommandResponse(message, tasks);
    }
}
