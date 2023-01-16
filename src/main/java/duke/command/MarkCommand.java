package duke.command;

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
     */
    @Override
    public CommandResponse run(String input, List<Task> tasks) {
        String arg = input.replaceFirst("mark ", "");
        int index = Integer.parseInt(arg) - 1;

        Task task = tasks.get(index);
        task = task.setDone(true);
        tasks.set(index, task);

        String message = String.format("Nice! I've marked this task as done:\n  %s", task.toString());

        return new CommandResponse(message, tasks);
    }
}
