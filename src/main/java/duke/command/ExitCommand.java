package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a command that exits the program.
 */
public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        ui.showGoodbyeMessage();
    }

    /**
     * Returns true to indicate that this is an exit command.
     * @return true
     */
    public boolean isExit() {
        return true;
    }
}
