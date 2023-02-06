package boo.command;

import boo.tasklist.TaskList;

/**
 * Represents a "list" command that is entered by the user.
 */
public class ListCommand extends Command {
    private TaskList tasks;

    /**
     * Constructs a {@code ListCommand}.
     */
    public ListCommand(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Causes the bot to print out the contents of the user's task list.
     *
     * @return a String containing the contents of the task list
     */
    @Override
    public String runCommand() {
        return tasks.getUserTasks();
    }
}
