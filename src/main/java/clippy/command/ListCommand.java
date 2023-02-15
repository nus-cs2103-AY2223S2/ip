package clippy.command;

import clippy.storage.Storage;
import clippy.task.TaskList;
import clippy.ui.Ui;

/**
 * Command handler for the `list` command.
 *
 * @author chunzkok
 */
public class ListCommand extends Command {
    /**
     * Lists all Tasks in the TaskList.
     *
     * @param ui The Ui instance of the current run.
     * @param taskList The TaskList instance of the current run.
     * @param storage The Storage instance of the current run.
     */
    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        String output = "";

        if (taskList.isEmpty()) {
            output = "No tasks found!";
        } else {
            output = taskList.describeAllTasks();
        }

        ui.prettyPrint(output);
    }
}
