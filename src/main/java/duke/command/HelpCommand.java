package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command class for help command.
 */
public class HelpCommand extends Command {
    /**
     * Prints the help message which contain all the commands available.
     * @param tasks
     * @param ui
     * @param storage
     * @return
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showHelp();
    }

    /**
     * Checks if this command will result in termination of duke.
     *
     * @return whether the program is exited.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
