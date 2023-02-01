package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command that terminates the Duke Program.
 */
public class ByeCommand extends Command {

    /**
     * Executes the command to signify to the user the termination of the Duke Program.
     * @param tasks TaskList containing all the currently stored Tasks.
     * @param ui Ui that deals with interactions with the user.
     * @param storage Storage that loads and saves tasks to the file containing currently stored Tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.show("Bye. Hope to see you again soon! :^)");
    }

    /**
     * Checks if the Command terminates the Duke Program.
     * @return true as ByeCommand terminates the Duke program.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
