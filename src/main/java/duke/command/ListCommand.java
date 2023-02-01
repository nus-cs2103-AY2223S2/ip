package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command that lists the currently stored Tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the command to list the currently stored Tasks.
     * @param tasks TaskList containing all the currently stored Tasks.
     * @param ui Ui that deals with interactions with the user.
     * @param storage Storage that loads and saves tasks to the file containing currently stored Tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int taskCount = tasks.size();
        if (taskCount == 0) {
            ui.show("You don't have any tasks now!");
        }
        else {
            for (int i = 0; i < taskCount; i++) {
                ui.show((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    /**
     * Checks if the Command terminates the Duke Program.
     * @return false as ListCommand does not terminate the Duke program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
