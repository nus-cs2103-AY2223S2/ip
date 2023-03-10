package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates list command.
 */
public class ListCommand extends Command {
    public static final String COMMAND = "list";

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.addToResponseMessage("Here are the tasks in your list:");
        int numOfTasks = tasks.size();
        for (int i = 1; i <= numOfTasks; i++) {
            ui.addToResponseMessage(i + "." + tasks.get(i - 1));
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
