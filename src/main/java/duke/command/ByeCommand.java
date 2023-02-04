package duke.command;

import duke.storage.StorageList;
import duke.task.TaskList;

/**
 * Implementation of Bye Command
 **/
public class ByeCommand extends Command {

    /**
     * Execute method for Bye Command to display message.
     * @param tasks   - task list of the current tasks.
     * @param storage - database of the history of commands.
     * @return String Display bye message when done.
     */
    public String execute(TaskList tasks, StorageList storage) {
        return "See you soon!";
    }

    /**
     * Check if program is to be exited upon bye command.
     *
     * @return boolean - true or false according to command.
     */
    public boolean isExit() {
        return true;
    }

}
