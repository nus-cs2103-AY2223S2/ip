package duke.command;

import duke.tasklist.TaskList;

/**
 * Represents a command that displays all the Tasks present in the list of Tasks.
 */
public class ListCommand extends Command {

    public ListCommand() {
        super(false);
    }

    /**
     * Executes the command by displaying all tasks present in the List of Tasks.
     *
     * @param taskList The TaskList object that manages the list of Tasks.
     */
    @Override
    public void execute(TaskList taskList) {
        taskList.listTasks();
    }

}
