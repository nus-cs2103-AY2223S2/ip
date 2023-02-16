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
     * @param tasks Stores all tasks.
     * @param ui       Handles all user interaction.
     * @param storage  Handles all storage of tasks in a file.
     * @return Returns string output.
     * @throws DukeException Checks if input is valid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String output = tasks.deleteTask(this.getInputArr(), ui, storage);
        System.out.println(output);
        return output;
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

    @Override
    public boolean isDataSourceChanged() {
        return false;
    }
}
