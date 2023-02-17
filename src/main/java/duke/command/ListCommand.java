package duke.command;

import duke.task.TaskList;

/**
 * A command to list activities.
 */
public class ListCommand {
    /**
     * Retrieves all the activities currently being tracked.
     * @param tasks a list of tasks.
     * @return A string of activities from the given list.
     */
    public static String executeList(TaskList tasks) {
        return "Here are the tasks in your list:\n" + tasks.listTasks();
    }
}
