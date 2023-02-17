package duke.command;

import duke.Task;
import duke.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Handles the appropriate tasks when performing a MarkCommand by Duke.
 */
public class MarkCommand extends Command {

    private int taskID;

    /**
     * Constructor for MarkCommand.
     * @param taskID index of the task to be marked within the list.
     */
    public MarkCommand(int taskID) {
        this.taskID = taskID;
    }

    /**
     * Overridden method to handle the specific tasks to be carried out when marking task as done in the list.
     * @param tasks a list of tasks.
     * @param ui Ui class to handle display messages.
     * @param storage Storage to handle saving/loading of data to/from the list of task.
     * @return Duke's response message
     */
    @Override
    public String initCommand(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.size() >= taskID && !tasks.isEmpty()) {
            Task currentTask = tasks.getTask(taskID);
            currentTask.marked();
            storage.saveData(tasks);
            return ui.displayMarkMessage(currentTask);

        } else {
            return ui.displayMsg("Invalid taskID entered!");
        }
    }
}
