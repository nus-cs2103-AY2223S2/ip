package catbot.commands;

import catbot.storage.Storage;
import catbot.tasklist.Task;
import catbot.tasklist.TaskList;
import catbot.ui.Ui;

/**
 * Handles listing the entire task list to the user.
 */
public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder taskList = new StringBuilder("List of tasks: \n");
        int index = 1;
        for (Task task: tasks) {
            taskList.append("  ").append(index++).append(".").append(task).append("\n");
        }

        ui.setNextOutput(taskList.toString());
    }
}
