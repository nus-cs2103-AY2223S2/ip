package duke.command;

import duke.storage.StorageList;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Implementation of Bye Command
 **/
public class ByeCommand extends Command {

    /**
     * Execute method for Bye Command to display message.
     * @param tasks   - task list of the current tasks.
     * @param ui      - interface of the command.
     * @param storage - database of the history of commands.
     * @return String Display bye message when done.
     */
    public String execute(TaskList tasks, Ui ui, StorageList storage) {
        return "See you soon!";
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
