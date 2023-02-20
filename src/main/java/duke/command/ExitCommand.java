package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

/**
 * Terminates the current session.
 */
public class ExitCommand extends Command {

    /**
     * Terminates the current session.
     * Ask UI to print output.
     *
     * @param tasks Task list.
     * @param ui UI of the application to interact with users.
     * @param storage Storage to update when there is an update with the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.showExit();
    }

    /**
     * Determines whether the program should be terminated.
     *
     * @return Boolean value if true then exit, if false then not exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}