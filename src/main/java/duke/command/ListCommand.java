package duke.command;

import duke.storage.CommandHistory;
import duke.storage.Storage;
import duke.task.DukeTask;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A ListCommand class that encapsulates the action of displaying all the tasks on the TaskList.
 */

public class ListCommand extends Command {
    /**
     * Displays all the tasks with their respective types and status.
     *
     * @param tasks   The user TaskList that contains all the task to be manipulated
     * @param ui      The ui Object used to display information
     * @param storage The Storage Object used to save and load the TaskList
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, CommandHistory commandHistory) {
        StringBuilder listContent = new StringBuilder("Here are the tasks in your list:\n");
        if (tasks.getNoOfTasks() == 0) {
            ui.appendResponse("There are no tasks in your list.");
            return;
        }
        int index = 1;
        for (DukeTask task : tasks.getTasks()) {
            listContent.append(index++).append(".").append(task).append("\n");
        }
        ui.appendResponse(listContent.toString());
    }
}