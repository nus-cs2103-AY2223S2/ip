package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * A class that handles exiting command.
 */
public class ExitCommand extends Command {

    /**
     * Indicates that the command is exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Displays goodbye message.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbyeMessage();
    }
}
