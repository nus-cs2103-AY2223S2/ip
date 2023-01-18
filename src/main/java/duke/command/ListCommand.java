package duke.command;

import duke.task.TaskList;

/**
 * Represents a list command.
 */
public class ListCommand implements Command {
    /**
     * Returns a message listing out the tasks in the task list.
     *
     * @param input {@inheritDoc}
     * @param tasks {@inheritDoc}
     * @return A message listing out the tasks in the task list.
     */
    @Override
    public String run(String input, TaskList tasks) {
        return tasks.toString();
    }
}
