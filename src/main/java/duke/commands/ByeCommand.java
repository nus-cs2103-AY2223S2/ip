package duke.commands;

import duke.utilities.Storage;
import duke.utilities.TaskList;
import duke.utilities.Ui;

/**
 * The {@code Command} class for the {@code bye} command.
 */
public class ByeCommand extends Command {

    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return "Exiting...";
    }

    public boolean isByeCommand() {
        return true;
    }
}
