package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a ByeCommand that implements the Command interface.
 */
public class ByeCommand implements Command {

    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        storage.saveData(tasks.retrieveList());
        return ui.displayExitMessage();
    }
}
