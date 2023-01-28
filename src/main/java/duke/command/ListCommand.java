package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to List all Tasks in TaskList.
 *
 * @author Lian Kok Hai
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.print(taskList.listTasks());
    }
}
