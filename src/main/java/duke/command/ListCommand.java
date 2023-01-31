package duke.command;

import duke.tasklist.TaskList;

/**
 * Represents a command that lists out all the Tasks present in the list of Tasks.
 */
public class ListCommand extends Command {

    public ListCommand() {
        super(false);
    }

    @Override
    public void execute(TaskList taskList) {
        taskList.listTasks();
    }

}
