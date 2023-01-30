package duke.command;

import duke.task.TaskList;

/**
 * Represents a list command for listing out the tasks in a task list.
 */
public class ListCommand implements Command {
    /**
     * Clears any keyphrase filter applied to the task list and returns a message listing out each task in tasks if
     * tasks is not empty. Otherwise, returns a message informing the user of the empty task list.
     *
     * @param input {@inheritDoc}
     * @param tasks {@inheritDoc}
     * @return A message listing out each task in tasks if tasks is not empty. Otherwise, returns a message informing
     *         the user of the empty task list.
     */
    @Override
    public String run(String input, TaskList tasks) {
        tasks.clearFilter();

        return tasks.getUnfilteredSize() == 0 ? "Nothing to see here. Now go on, scram!" : tasks.toString();
    }
}
