package duke.commands;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

public class ListCommand extends Command {


    /**
     * Displays all tasks in duke.task.TaskList
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.getSize() == 0) {
            ui.showToUser("Task list is empty.");
        } else {
            ui.showToUser("Here are your tasks: ");
            int count = 1;
            for (Task t : tasks.getList()) {
                ui.showToUser(count + ". " + t.toString());
                count++;
            }
        }
    }
}
