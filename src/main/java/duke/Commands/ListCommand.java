package duke.Commands;

import duke.Exceptions.DukeException;
import duke.Storage.Storage;
import duke.TaskList.TaskList;
import duke.Ui.Ui;
/**
 * ListCommand class that implements the Command interface.
 */
public class ListCommand implements Command {

    /** 
     * Retrieves the current task list.
     * 
     * @param tasks The current task list.
     * @param ui The ui object that handles printing to the user.
     * @param storage The storage object that handles saving to the data file.
     * @throws DukeException Exception should not be thrown in this function in this class.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.size() == 0)
            ui.printNoTasks();
        else
            ui.printTasks(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
