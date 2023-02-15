package rick.command;

import rick.TaskList;
import rick.Ui;

/**
 * Represents a user command issued to the rick.Rick app.
 *
 * @author SeeuSim
 *         AY2223-S2 CS2103T
 */
public abstract class Command {
    /**
     * Executes this command with the given TaskList and UI output, and
     * returns the UI to output to the user.
     *
     * @param ts The TaskList instance.
     * @param ui The UI output.
     * @return The system UI to output to the GUI from executing this command.
     */
    public abstract String execute(TaskList ts, Ui ui);

    /**
     * Returns a boolean indicating if this is the Exit command.
     *
     * @return True if it is an Exit command.
     */
    public boolean isExit() {
        return false;
    }
}
