package red.command;

import red.storage.Storage;
import red.task.TaskList;
import red.ui.UI;

/**
 * An ExitCommand class that encapsulates the event of terminating the Duke program.
 */

public class ExitCommand extends Command {
    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";
    /**
     * Displays the goodbye message and terminate the program.
     *
     * @param tasks The user TaskList that contains all the task to be manipulated
     * @param ui The ui Object used to display information
     * @param storage The Storage Object used to save and load the TaskList
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        System.out.println(GOODBYE_MESSAGE);
    }

    /**
     * Indicates that this Command is the exit Command.
     *
     * @return always return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
