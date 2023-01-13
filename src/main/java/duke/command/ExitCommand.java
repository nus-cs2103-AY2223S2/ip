package duke.command;

import duke.display.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * An ExitCommand class that encapsulates the event of terminating the Duke program.
 */

public class ExitCommand extends Command {
    /**
     * Displays the goodbye message and terminate the program.
     *
     * @param tasks The user TaskList that contains all the task to be manipulated
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.displayWithBar("Bye. Hope to see you again soon!");
        System.exit(0);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
