package duke.command;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an add command.
 */
public abstract class AddCommand implements Command {
    /**
     * Returns a CommandResponse object containing an acknowledgement message and an updated task list with the new task
     * added.
     *
     * @param input {@inheritDoc}
     * @param tasks {@inheritDoc}
     * @return CommandResponse object containing an acknowledgement message and an updated task list with the new task
     * added.
     */
    @Override
    public CommandResponse run(String input, List<Task> tasks) {
        List<Task> updatedTasks = new ArrayList<Task>(tasks);
        Task task = createTask(input);
        updatedTasks.add(task);

        String message = String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.",
                task.toString(), updatedTasks.size());

        return new CommandResponse(message, updatedTasks);
    }

    /**
     * Returns a Task object created using the provided input.
     *
     * @param input User's input.
     * @return Task object created using the provided input.
     */
    protected abstract Task createTask(String input);
}
