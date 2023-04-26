package duke.commands;

import java.util.ArrayList;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * A class that handles listing all tasks.
 */
public class ListCommand extends Command {

    public ListCommand() {
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showToUser("Here are the tasks in your list: ");
        ArrayList<Task> userTasks = tasks.getTasks();
        for (int i = 1; i <= userTasks.size(); i++) {
            ui.showToUser(i + "." + userTasks.get(i - 1).toString() + "\n");
        }
    }
}
