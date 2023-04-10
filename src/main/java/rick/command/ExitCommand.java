package rick.command;

import rick.TaskList;
import rick.Ui;

/**
 * Represents the command that exits the rick.Rick app.
 *
 * @author SeeuSim
 *         AY2223-S2 CS2103T
 */
public class ExitCommand extends Command {
    /**
     * Executes this command with the given TaskList and UI output, and
     * returns the UI to output to the user.
     *
     * @param ts The TaskList instance.
     * @param ui The UI output.
     */
    @Override
    public String execute(TaskList ts, Ui ui) {
        return ui.exitMessage();
    }

    /**
     * Returns a boolean indicating that this is an Exit command.
     * @return True.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
