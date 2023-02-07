package duke.command;

import duke.Task;
import duke.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

public class UnmarkCommand extends Command {
    private int taskID;

    /**
     * Constructor for UnmarkCommand.
     * @param taskID index of the task to be unmark within the list.
     */
    public UnmarkCommand(int taskID) {
        this.taskID = taskID;
    }

    /**
     * Overridden method to handle the specific tasks to be carried out when unmarking task from the list.
     * @param tasks a list of tasks.
     * @param ui Ui class to handle display messages.
     * @param storage Storage to handle saving/loading of data to/from the list of task.
     */
    @Override
    public void initCommand(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.size() >= taskID && !tasks.isEmpty()) {
            Task currentTask = tasks.getTask(taskID);
            currentTask.unmarked();
            storage.saveData(tasks);
            ui.displayUnmarkedMessage(currentTask);

        } else {
            ui.displayMsg("Invalid taskID entered!");
        }
    }

}
