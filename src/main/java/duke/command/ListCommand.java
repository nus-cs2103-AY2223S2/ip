package duke.command;

import duke.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

public class ListCommand extends Command{
    /**
     * Overridden method to handle the specific tasks to be carried out when listing out all tasks from the Duke's list.
     * @param tasks a list of tasks.
     * @param ui Ui class to handle display messages.
     * @param storage Storage to handle saving/loading of data to/from the list of task.
     */
    @Override
    public void initCommand(TaskList tasks, Ui ui, Storage storage) {
        ui.displayTaskList(tasks);
    }
}
