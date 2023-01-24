package duke.commands;

import duke.utilities.Storage;
import duke.utilities.TaskList;
import duke.utilities.Ui;

public class ByeCommand extends Command {

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showMessage("Exiting...");
    }

    public boolean isByeCommand() {
        return true;
    }
}