package dukes.command;

import dukes.util.DukeException;
import dukes.util.TaskList;
import dukes.util.UI;
import dukes.util.Storage;

/**
 * Subclass of Command that handles the command to exit Duke.
 */
public class ExitCommand extends Command {

    /**
     * Constructor of ExitCommand class.
     *
     * @param isExit show if the command is an ExitCommand. True for all ExitCommand.
     * @param isValid show if the command is valid.
     * @param header the type the command belongs to, e.g. "add", "delete".
     * @param body nothing.
     */
    public ExitCommand(boolean isExit, boolean isValid,
                       String header, String body) {
        super(isExit, isValid, header, body);
    }

    /**
     * Provide goodbye message to user.
     *
     * @param tasks contains the task list.
     * @param ui the UI in charge of user interactions.
     * @param storage handles the loading and saving of files.
     * @throws DukeException if the index provided is out of bounds.
     * @return method feedback
     */
    public String runCommand(TaskList tasks, UI ui, Storage storage) throws DukeException {
        return "Gooodbye, wish you a nice day!";
    }

}
