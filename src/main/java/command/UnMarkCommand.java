package command;

import exception.DukeException;
import main.Storage;
import main.Ui;
import main.TaskList;

/**
 * Encapsulates unmark command
 */
public class UnMarkCommand implements Command {
    private int inputIndex;

    public UnMarkCommand(int indexToMark) {
        this.inputIndex = indexToMark;
    }

    /**
     * Executes the task when user inputs "unmark"
     * 
     * @param list    the list in duke to store the user's Task
     * @param ui      the user interface that is present in duke
     * @param storage the storage to store the task in the TaskList list
     * @throws DukeException if there's an error
     */
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        if (inputIndex <= 0 || inputIndex > list.size()) {
            throw new DukeException("OOPS!!! The index to unmark cannot be less than 0 or "
                    + "greater than the length of the list.");
        }
        list.unmark(inputIndex);
        ui.printUnMarkedTask(list.get(inputIndex));
    }

    /**
     * Checks whether the task exits the programme
     * 
     * @return true if this command exits the programme, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}