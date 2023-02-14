package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a command that exits the program.
 */
public class ExitCommand extends Command {

    /**
     * Returns the command as a String which is used to show to the user in the GUI.
     * Executes the command and exits the program.
     *
     * @param tl TaskList which the Duke will modify.
     * @param ui Ui to be used to facilitate interactions between user and the CLI.
     * @param storage Storage to be used to handle interactions with the save file.
     */
    @Override
    public String execute(TaskList tl, Ui ui, Storage storage) {
        ui.showGoodbyeMessage();
        return ui.showGoodbyeMessage();
    }

    /**
     * Returns true to indicate that this is an exit command, false otherwise.
     *
     * @return true
     */
    public boolean isExit() {
        return true;
    }
}
