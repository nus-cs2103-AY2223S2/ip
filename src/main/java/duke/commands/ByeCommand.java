package duke.commands;

import duke.duke.Ui;
import duke.exceptions.StorerEmptyException;
import duke.storage.Storage;
import duke.storage.TaskList;

public class ByeCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) throws StorerEmptyException {
        ui.display("BYE! Hope to see you again soon!");
    }

    /**
     * Notifies the programme that the user wishes to quit.
     * @return
     */
    public boolean isBye() {
        return true;
    }
}
