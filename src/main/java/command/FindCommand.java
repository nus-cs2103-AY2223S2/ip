package command;

import exception.DukeException;
import main.TaskList;
import main.Ui;
import main.Storage;

/**
 * Encapsulates Events command
 */
public class FindCommand implements Command {
    String input;

    public FindCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the task when user inputs "list"
     * 
     * @param list    the list in duke to store the user's Task
     * @param ui      the user interface that is present in duke
     * @param storage the storage to store the task in the TaskList list
     * @throws DukeException if there's an error
     */
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        TaskList result = list.find(input);
        ui.list(result);
        storage.save(list);
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
