package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.DukeException;
import duke.Storage;

/**
 * Command: to list out the commands
 */
public class ListCommand extends Command {
    /**
     * Executes the command
     *
     * @param tasks
     * @param ui
     * @param storage
     * @throws DukeException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showList(tasks);
    }

    /**
     * Checks if this command will exit the program
     *
     * @return boolean True if the command will exit the program
     */
    public boolean isExit() {
        return false;
    }
}