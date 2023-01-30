package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents the command to exit the program.
 */
public class ExitCommand extends Command {

    public ExitCommand() {
        super();
    }

    /**
     * Heeds user goodbye via the ui.
     * @param tasks TaskList that contains all the current tasks.
     * @param ui Ui that communicates with the user.
     * @param storage Storage that backups the saving of tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Bye~ Hope to see you again soon!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
