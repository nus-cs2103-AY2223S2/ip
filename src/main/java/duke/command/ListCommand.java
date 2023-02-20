package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

/**
 * Shows list of tasks.
 */
public class ListCommand extends Command {
    /**
     * Asks UI to list the tasks.
     *
     * @param tasks Task list.
     * @param ui UI of the application to interact with users.
     * @param storage Storage to update when there is an update with the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.ShowList(tasks.getAllTasks());
    }
}