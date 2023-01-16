package duke.command;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an add command.
 */
public class AddCommand implements Command {
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
        updatedTasks.add(new Task(false, input));

        return new CommandResponse(String.format("added: %s", input), updatedTasks);
    }
}
