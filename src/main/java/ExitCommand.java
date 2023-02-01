package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command from the user to save and exit program.
 *
 * @author Karen
 */
public class ExitCommand extends Command {

    /**
     * Checks if command is an exit command.
     *
     * @return true
     */
    public boolean isExit() {
        return true;
    }

    /**
     * Saves current list of task in a file. Prints message indicating to user that list of task have been saved
     * succefully. Exits programme.
     *
     * @param tasks A TaskList containing the set of task the user has.
     * @param ui An Ui which allows for interaction between Duke and user.
     * @param storage A Storage enabling Duke to store memory.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.saveData(tasks);
        } catch (IOException e) {
            ui.unableToSaveErrorMessage();
        } finally {
            ui.exitResponse();
        }
    }

}
