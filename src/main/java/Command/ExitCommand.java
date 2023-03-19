package duke.command;

import java.io.IOException;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command from the user to save and exit program.
 */
public class ExitCommand extends Command {

    /**
     * Checks if command is an exit command.
     *
     * @return true.
     */
    public boolean isExit() {
        return true;
    }

    /**
     * Saves current list of task in a file. Prints message indicating to user that list of task have been saved
     * succefully. Exits programme.
     *
     * @param tasks A TaskList containing the set of task the user has.
     * @param storage A Storage enabling Duke to store memory.
     * @return String The String message indicating status of action.
     * @throws DukeException
     */
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        boolean isSaved = true;
        try {
            storage.saveData(tasks);
            isSaved = true;
        } catch (IOException e) {
            isSaved = false;
        }
        return Ui.endResponse(isSaved);
    }
}
