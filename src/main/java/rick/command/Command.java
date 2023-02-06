package rick.command;

import rick.TaskList;
import rick.Ui;

/**
 * The main abstraction that represents user commands issued to the rick.Rick app.
 *
 * @author SeeuSim
 *         AY2223-S2 CS2103T
 */
public abstract class Command {
    /**
     * Execute this command with the given TaskList and UI output.
     *
     * @param ts The TaskList instance.
     * @param ui The UI output.
     * @return The system UI to output to the GUI from executing this command.
     */
    public abstract String execute(TaskList ts, Ui ui);

    /**
     * Indicate if this is the Exit command.
     *
     * @return True if it is an Exit command.
     */
    public boolean isExit() {
        return false;
    }
}
