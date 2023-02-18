package commands;

import storage.Storage;
import storage.TaskList;
import ui.Ui;

/**
 * An abstract class to represent various commands
 */
public abstract class Command {

    /**
     * Executes actions of respective commands
     *
     * @param taskList the list to perform the execution on
     * @param ui the user interface to output the result
     * @param storage the database
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);
}
