package command;

import dukeexception.DukeException;
import store.Storage;
import store.TaskList;
import userinteraction.Ui;

/**
 * Command class for deleting tasks.
 */
public class DeleteTaskCommand extends Command {

    /**
     * Public constructor.
     *
     * @param inputArr String array from user input.
     */
    public DeleteTaskCommand(String[] inputArr) {
        super(inputArr);
    }

    /**
     * Deletes task from tasklist and file.
     *
     * @param taskList Stores all tasks.
     * @param ui       Handles all user interaction.
     * @param storage  Handles all storage of tasks in a file.
     * @throws DukeException Checks if input is valid.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.deleteTask(inputArr, ui);
        storage.writeData(taskList);
    }

    /**
     * Does not exit program.
     *
     * @return Boolean to exit program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
