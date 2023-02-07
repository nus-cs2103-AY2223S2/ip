package red.command;

import red.storage.Storage;

import red.task.TaskList;

import red.ui.UI;

/**
 * This class specifies the act of listing out the current list of tasks.
 */

public class ListCommand extends Command {

    /**
     * Displays all the tasks with their respective types and status.
     *
     * @param tasks The TaskList that contains the current list of tasks.
     * @param ui The UI that interprets any user inputs.
     * @param storage The Storage that keeps track of how the list of tasks changes from user input.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        boolean isTaskListEmpty = tasks.getTaskListSize() == 0;
        if (isTaskListEmpty) {
            System.out.println("There are no tasks in your list.");
            return;
        }
        ui.addCurrentReply(String.valueOf(tasks));
        System.out.println(tasks);
    }
}
