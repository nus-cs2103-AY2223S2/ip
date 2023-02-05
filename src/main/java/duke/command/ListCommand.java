package duke.command;

import duke.task.TaskList;

/**
 * Represents a list command for listing out the tasks in a task list.
 */
public class ListCommand implements Command {
    /**
     * Returns a message listing out each task in tasks if tasks is not empty. Otherwise, returns a message informing
     * the user of the empty task list.
     *
     * @param input {@inheritDoc}
     * @param tasks {@inheritDoc}
     * @return A message listing out each task in tasks if tasks is not empty. Otherwise, a message informing the user
     *         of the empty task list.
     */
    @Override
    public String run(String input, TaskList tasks) {
        assert tasks != null;

        return tasks.size() == 0 ? "Nothing to see here. Now go on, scram!" : tasks.toString();
    }
}
