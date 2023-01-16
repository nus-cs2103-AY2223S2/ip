package duke.command;

import duke.task.Task;

import java.util.List;

/**
 * Represents a list command.
 */
public class ListCommand implements Command {
    /**
     * Returns a CommandResponse object containing a message listing out the tasks in the task list and the task list
     * with no changes.
     *
     * @param input {@inheritDoc}
     * @param tasks {@inheritDoc}
     * @return CommandResponse object containing a message listing out the tasks in the task list and the task list with
     * no changes.
     */
    @Override
    public CommandResponse run(String input, List<Task> tasks) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tasks.size(); ++i) {
            stringBuilder.append(String.format("%d.%s\n", i + 1, tasks.get(i).toString()));
        }

        return new CommandResponse(stringBuilder.toString().trim(), tasks);
    }
}
