package duke.command;

import duke.TaskList;
import duke.Ui;

/**
 * The command that exits the Duke app.
 *
 * @author SeeuSim
 * AY2223-S2 CS2103T
 */
public class ExitCommand extends Command {
    /**
     * Executes this command.
     * @param ts The TaskList instance.
     * @param ui The UI output.
     */
    @Override
    public void execute(TaskList ts, Ui ui) {
        ui.exitMessage();
    }

    /**
     * Indicate that this is an Exit command.
     * @return True.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
