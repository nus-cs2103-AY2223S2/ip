package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command to be executed when user wants to see all the tasks to be completed.
 *
 * @author Cheam Jia Wei
 */
public class ListCommand extends Command {
    public void execute(TaskList taskList, Ui inter, Storage store) {
        inter.list(taskList);
    }

    public boolean isExit() {
        return false;
    }
}
