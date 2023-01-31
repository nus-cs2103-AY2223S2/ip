package duke.commands;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

public class ListCommand extends Command {


    /**
     * Displays all tasks in TaskList
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.getSize() == 0) {
            ui.showToUser("Task List is currently empty.");
        } else {
            ui.showToUser("Here are your tasks: ");
            ui.showIndexedList(tasks.getList());
        }

    }
}
