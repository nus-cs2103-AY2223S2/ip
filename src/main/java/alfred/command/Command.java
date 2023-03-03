package alfred.command;

import alfred.exceptions.AlfredException;
import alfred.storage.Storage;
import alfred.task.TaskList;
import alfred.ui.Ui;

/**
 * Abstracts all the commands available from the user and its methods.
 */
public abstract class Command {

    /**
     * Executes the command given by manipulating the tasks, updating the storage and displaying
     * any results back to the ui.
     * @param tasks The tasks that are to be operated by the command.
     * @param ui The Ui that the user will interact with.
     * @param storage The storage that handle the loading and writing of the tasks.
     * @throws AlfredException The error that is thrown when the command fails to execute.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws AlfredException;

    /**
     * Checks if the command will result in the end of the program.
     * @return True if the command closes the program, else false.
     */
    public abstract boolean isExit();
}
