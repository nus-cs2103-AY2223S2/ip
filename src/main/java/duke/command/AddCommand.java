package duke.command;

import duke.Task;
import duke.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Handles the appropriate tasks when performing an AddCommand by Duke.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructor for AddCommand.
     * @param task Task provided by the user.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Overridden method to handle the specific tasks to be carried out when adding tasks.
     * @param tasks a list of tasks.
     * @param ui Ui class to handle display messages.
     * @param storage Storage to handle saving/loading of data to/from the list of task.
     * @return Duke's response message
     */
    @Override
    public String initCommand(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(this.task);
        storage.saveData(tasks);
        return ui.displayAddTaskMessage(task, tasks);
    }
}
