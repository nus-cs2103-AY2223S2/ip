
package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.enums.Views;

/**
 * Command: Returns the Export message
 */
public class ExportCommand extends Command {

    /**
     * Creates Export Command
     *
     * @param query string from user
     */
    public ExportCommand() {
    }

    /**
     * Executes the command
     *
     * @param tasks   TaskList object to get and set the list
     * @param storage object required when command writes to file
     * @throws DukeException
     */
    public void execute(TaskList tasks, Storage storage) throws DukeException {
        storage.saveMarkdown(tasks);
        Ui.printer(Views.EXPORT_MD_STRING);
    }

    /**
     * Executes the command
     *
     * @param tasks   TaskList object to get and set the list
     * @param storage object required when command writes to file
     * @return returns the UI text instead of printing
     * @throws DukeException
     */
    public String executeString(TaskList tasks, Storage storage) throws DukeException {
        storage.saveMarkdown(tasks);
        return Views.EXPORT_MD_STRING.str();
    }

    /**
     * Checks if this command will exit the program
     *
     * @return boolean True if the command will exit the program
     */
    public boolean isExit() {
        return false;
    }
}
