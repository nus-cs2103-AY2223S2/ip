package chad.command;

import chad.storage.TaskList;

/**
 * Class use to handle command: view task list.
 * Allows user to view the task list.
 */
public class ListCommand extends Command {
    /**
     * Execute the <code>List</code> task.
     * @param tasks the list to store new task.
     * @return All tasks in task list.
     */
    @Override
    public String execute(TaskList tasks) {
        // if there is no task in the list
        if (tasks.numOfTask() == 0) {
            return "List is empty";
        }
        return tasks.getTaskList();
    }
}
