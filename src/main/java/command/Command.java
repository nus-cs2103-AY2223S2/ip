package command;

import exception.DukeException;
import main.Storage;
import main.Ui;
import main.TaskList;

/**
 * Encapsulates user's command
 */
public interface Command {
    /**
     * 
     * @param list    the list in duke to store the user's Task
     * @param ui      the user interface that is present in duke
     * @param storage the storage to store the task in the TaskList list
     * @throws DukeException if there's an error
     */
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException;

    /**
     * Checks whether the task exits the programme
     * 
     * @return true if this command exits the programme, false otherwise.
     */
    public boolean isExit();
}
