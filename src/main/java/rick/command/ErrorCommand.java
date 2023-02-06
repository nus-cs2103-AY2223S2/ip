package rick.command;

import rick.TaskList;
import rick.Ui;
import rick.exceptions.RickException;

/**
 * The command that represents an error parsing the user given command.
 *
 * @author SeeuSim
 *         AY2223-S2 CS2103T
 */
public class ErrorCommand extends Command {
    private final String msg;

    /**
     * Formats this command with the given error message.
     *
     * @param msg The error message to display.
     */
    public ErrorCommand(String msg) {
        this.msg = msg;
    }

    /**
     * Formats this command with the given error.
     *
     * @param de The exception to populate this command with.
     */
    public ErrorCommand(RickException de) {
        this.msg = de.getMessage();
    }

    /**
     * Executes this command.
     *
     * @param ts The TaskList instance.
     * @param ui The UI instance.
     * @return The UI to output to the GUI.
     */
    @Override
    public String execute(TaskList ts, Ui ui) {
        return ui.error(new RickException(msg));
    }
}
