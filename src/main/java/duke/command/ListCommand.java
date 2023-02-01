package duke.command;

import duke.storage.TaskList;

/**
 * Class use to handle command: view duke.task list.
 * Allows user to view the duke.task list.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks) {
        if (tasks.numOfTask() == 0) {
            return "List is empty";
        }
        return tasks.getTaskList();
    }
}
