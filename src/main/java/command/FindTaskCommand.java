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
     * @param tasks Stores all tasks.
     * @param ui       Handles all user interaction.
     * @param storage  Handles all storage of tasks in a file.
     * @return Returns string output.
     * @throws DukeException Checks if input is valid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String output = tasks.findTask(this.getInputArr());
        System.out.println(output);
        return output;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean isDataSourceChanged() {
        return false;
    }
}
