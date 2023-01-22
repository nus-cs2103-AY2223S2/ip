package duke.Commands;

import duke.Exceptions.DukeException;
import duke.Exceptions.TaskException;
import duke.Storage.Storage;
import duke.TaskList.TaskList;
import duke.Ui.Ui;
/**
 * DeleteCommand class that implements the Command interface.
 */
public class DeleteCommand implements Command {

    private String fullCommand;

    public DeleteCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /** 
     * Deletes a task in the task list.
     * 
     * @param tasks The current task list.
     * @param ui The ui object that handles printing to the user.
     * @param storage The storage object that handles saving to the data file.
     * @throws DukeException If there is an exception due to invalid input.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            int taskNum = Integer.parseInt(fullCommand.substring(7));
            if (taskNum > tasks.size() || taskNum < 1)
                throw new TaskException("Please enter a valid task number!");
            tasks.deleteTask(taskNum, storage, ui);
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            throw new TaskException("Please enter a valid task number!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
