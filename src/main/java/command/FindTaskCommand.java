package command;

import dukeexception.DukeException;
import store.Storage;
import store.TaskList;
import userinteraction.Ui;

/**
 * Command class for finding tasks.
 */
public class FindTaskCommand extends Command {
    /**
     * Public constructor.
     *
     * @param inputArr String array from user input.
     */
    public FindTaskCommand(String[] inputArr) {
        super(inputArr);
    }

    /**
     * Finds tasks.
     *
     * @param taskList Stores all tasks.
     * @param ui       Handles all user interaction.
     * @param storage  Handles all storage of tasks in a file.
     * @throws DukeException Checks if input is valid.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.findTask(this.getInputArr());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
