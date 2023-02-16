package command;

import main.DukeException;
import main.Storage;
import main.TaskList;
import main.Ui;

/**
 * Takes in the details of a command and performs the command.
 */
public abstract class Command {

    /**
     * Executes the actions.
     *
     * @param taskList List of task.
     * @param ui Ui.
     * @param storage Storage.
     * @throws DukeException Throws exception if user input is invalid.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {};

    /**
     * Returns whether a command is an exit command.
     *
     * @return A boolean that represents whether the command is an exit command.
     */
    public boolean isExit() {
        return false;
    }
}
