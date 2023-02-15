package duke.command;
import java.io.IOException;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command that is used to end Duke when executed.
 */
public class EndCommand extends Command {

    /**
     * Class constructor of EndCommand.
     */
    public EndCommand() {}

    /**
     * Ends Duke and saves the TaskList into the storage.
     * @param tasks the TaskList of the Duke
     * @param ui the Ui of the Duke
     * @param storage the storage of the Duke
     * @return the message to indicate end of Duke
     * @throws DukeException if error occurs during saving of TaskList data
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            storage.writeToFile(tasks.toTxtString());
        } catch (IOException e) {
            System.out.println("Error during saving");
        }
        return ui.end();
    }

    /**
     * Returns true when the command indicates the closure of the software.
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
