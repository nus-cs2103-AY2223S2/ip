package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The HelpCommand class implements the action of showing all the commands.
 *
 * @author Chia Jeremy
 */
public class HelpCommand extends Command {

    /**
     * Executes the help command.
     * List all available commands to the user.
     *
     * @param storage the file to save the tasks
     * @param tasks   the task lists
     * @param ui      the interface that deals with interactions with the user
     */
    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        ui.setResponse(ui.showCommands());
    }
}
