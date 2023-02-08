package command;

import dukeexception.DukeException;
import store.Storage;
import store.TaskList;
import userinteraction.Ui;

/**
 * Command class for listing all tasks.
 */
public class ListCommand extends Command {
    /**
     * Public constructor.
     *
     * @param inputArr String array from user input.
     */
    public ListCommand(String[] inputArr) {
        super(inputArr);
    }

    /**
     * Lists all tasks.
     *
     * @param tasks Stores all tasks.
     * @param ui       Handles all user interaction.
     * @param storage  Handles all storage of tasks in a file.
     * @return Returns string output.
     * @throws DukeException Checks if input is valid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return tasks.listTask(this.getInputArr());
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
