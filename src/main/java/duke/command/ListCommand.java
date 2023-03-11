package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.UI.UI;

/**
 * The list command.
 * Extends from Command.
 * Lists down all the tasks saved in the storage.
 */
public class ListCommand extends Command {
    public static final String COMMAND = "list";

    @Override
    public void runCommand(TaskList taskList, UI ui, Storage storage) {
        ui.printResponse("Here are the tasks in your list: \n");
        int numberOfTasks = taskList.numberOfTasks();

        for (int i = 1; i <= numberOfTasks; i++) {
            ui.printResponse(i + "." + taskList.getTask(i - 1));
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
