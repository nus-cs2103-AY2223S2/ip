package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The duke.commands.HelpCommand class implements the action of showing all the duke.commands.
 *
 * @author Chia Jeremy
 */

public class HelpCommand extends Command {

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        ui.display(ui.showCommands());
    }
}
