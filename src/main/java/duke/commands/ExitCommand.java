package duke.commands;

import duke.storage.Storage;
import duke.tasks.Tasks;
import duke.ui.Ui;

/**
 * Represents the command for exiting the Duke.
 */
public class ExitCommand extends Command {

    @Override
    public String execute(Tasks tasks, Ui ui, Storage storage) {
        return "exit";
    }
}
